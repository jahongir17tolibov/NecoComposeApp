# NecoComposeApp
Simple jetpack compose android app for growing skills.
The main feature of my code is the use of MVI architecture.

MVI architecture guide for one screen (HomeScreen)

ProductsListRoute.kt  ->  StoreViewModel.kt  ->  ProductListRoute(in HomeScreen.kt)  ->  HomeScreen(in HomeScreen.kt)
And, of course, the file with the helper functions to do this is: ComposeArchHelper.kt
