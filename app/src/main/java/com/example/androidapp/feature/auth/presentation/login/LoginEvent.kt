package com.example.androidapp.feature.auth.presentation.login

sealed interface LoginEvent {

    data class UsernameChanged(
        val username: String
    ): LoginEvent

    data class PasswordChanged(
        val password: String
    ): LoginEvent

    data object PasswordVisibilityChanged : LoginEvent

    data object LoginClicked : LoginEvent
}