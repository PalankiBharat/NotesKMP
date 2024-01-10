package com.bharat.noteskmp.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import presentation.Auth.AuthViewStates
import presentation.Auth.LoginPage


@Preview
@Composable
fun LoginCardPrev() {
    LoginPage(loginViewStates = AuthViewStates(
        loginEmail = null,
        loginPassword = null,
        signupEmail = null,
        signupName = null,
        signupPassword = null,
        loginResponseToken = null,
        message = null
    )){

    }
}