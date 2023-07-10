package com.example.necocomposeapp.uicomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.necocomposeapp.R
import com.example.necocomposeapp.ui.theme.ErrorLeadIconColor
import com.example.necocomposeapp.ui.theme.HintColorBlue
import com.example.necocomposeapp.ui.theme.LeadingIconDarkBlueColor

@Preview(showBackground = true)
@Composable
fun PreviewFields() = Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(20.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
) {

    LoginTextField(
        labelText = "Type your Login",
        leadingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Rounded.Email, contentDescription = "Email")
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            autoCorrect = true,
            imeAction = ImeAction.Go
        ),
        trailingIcon = null
    )

    var passwordVisible by remember { mutableStateOf(false) }

    LoginTextField(
        labelText = "Type your Password",
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.round_key_24),
                    contentDescription = "pass_code"
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            autoCorrect = false,
            imeAction = ImeAction.Go
        ),
        trailingIcon = {
            val image = if (passwordVisible) painterResource(id = R.drawable.baseline_visibility_24)
            else painterResource(id = R.drawable.baseline_visibility_off_24)

            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(painter = image, contentDescription = description)
            }
        }
    )

    LoginTextField(
        labelText = "Retype your Password",
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.round_key_24),
                    contentDescription = "re_pass_code"
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            autoCorrect = false,
            imeAction = ImeAction.Done
        ),
        trailingIcon = {
            val image = if (passwordVisible) painterResource(id = R.drawable.baseline_visibility_24)
            else painterResource(id = R.drawable.baseline_visibility_off_24)

            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(painter = image, contentDescription = description)
            }
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextField(
    labelText: String,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation? = null,
    leadingIcon: @Composable (() -> Unit)?,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = { newText ->
            text = newText
        },
        label = {
            Text(text = labelText, overflow = TextOverflow.Ellipsis)
        },
        textStyle = TextStyle(fontFamily = FontFamily.Monospace, fontSize = 16.sp),
        singleLine = true,
        visualTransformation = visualTransformation ?: VisualTransformation.None,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = keyboardOptions,
        placeholder = {
            Text(text = "Type here...", color = HintColorBlue, fontSize = 16.sp)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.DarkGray,
            unfocusedLabelColor = Color.DarkGray,
            unfocusedLeadingIconColor = Color.DarkGray,
            unfocusedTrailingIconColor = Color.DarkGray,
            focusedBorderColor = Color.Blue,
            focusedLabelColor = Color.Blue,
            focusedLeadingIconColor = LeadingIconDarkBlueColor,
            focusedTrailingIconColor = Color.Blue,
            errorBorderColor = Color.Red,
            errorLabelColor = Color.Red,
            errorLeadingIconColor = ErrorLeadIconColor,
            errorTrailingIconColor = Color.Red,
//            textColor = LeadingIconDarkBlueColor
        )
//        TextFieldDefaults.run {
//            colors(
////            unfocusedBorderColor = Color.DarkGray,
//                unfocusedLabelColor = Color.DarkGray,
//                unfocusedLeadingIconColor = Color.DarkGray,
//                unfocusedTrailingIconColor = Color.DarkGray,
////            focusedBorderColor = Color.Blue,
//                focusedLabelColor = Color.Blue,
//                focusedLeadingIconColor = LeadingIconDarkBlueColor,
//                focusedTrailingIconColor = Color.Blue,
////            errorBorderColor = Color.Red,
//                errorLabelColor = Color.Red,
//                errorLeadingIconColor = ErrorLeadIconColor,
//                errorTrailingIconColor = Color.Red,
////            textColor = LeadingIconDarkBlueColor
//            )
//
//
//        }
        ,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
    )

}