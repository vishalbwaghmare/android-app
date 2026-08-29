package com.example.androidapp.feature.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class LoginViewModel : ViewModel(){

    private  val _loginState = MutableStateFlow(LoginUiState())

    val loginState : StateFlow<LoginUiState> = _loginState.asStateFlow()

    private val _loginSuccess = MutableSharedFlow<Unit>()

    val loginSuccess = _loginSuccess.asSharedFlow()

    fun onEvent(event: LoginEvent){
        when (event){

            is LoginEvent.UsernameChanged -> {

                _loginState.update {
                    it.copy(
                        username = event.username,
                        errorMessage = null
                    )
                }
            }

            is LoginEvent.PasswordChanged -> {

                _loginState.update {
                    it.copy(
                        password = event.password,
                        errorMessage = null,
                    )
                }
            }

            is LoginEvent.PasswordVisibilityChanged -> {

                _loginState.update {
                    it.copy(
                        isPasswordVisible = !it.isPasswordVisible
                    )
                }
            }

            LoginEvent.LoginClicked -> {
                login()
            }
        }
    }

    private  fun login(){

        val state = _loginState.value

        if (state.username.isBlank()){

            _loginState.update {
                it.copy(
                    errorMessage = "Username is required"
                )
            }
            return
        }

        if(state.password.isBlank()){

            _loginState.update {
                it.copy(
                    errorMessage = "Password is required"
                )
            }
            return
        }

        viewModelScope.launch {

            _loginState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }
            delay(1500.milliseconds)

            _loginState.update {
                it.copy(
                    isLoading = false,
                )
            }

            _loginSuccess.emit(Unit)
        }
    }
}