package com.example.androidapp.feature.auth.presentation.login

import com.example.androidapp.core.design.components.RcTextFiled
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.androidapp.core.design.AppSpacing
import com.example.androidapp.core.design.AppTypography
import com.example.androidapp.core.design.components.RcPasswordTextField
import com.example.androidapp.core.design.components.RcPrimaryButton

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit
){
    val state by viewModel.loginState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loginSuccess.collect {
            onLoginSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(AppSpacing.lg),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome Back",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(AppSpacing.sm)
        )

        Text(
            text = "Login to continue",
            style = AppTypography.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier.height(AppSpacing.lg)
        )

        RcTextFiled(
            value = state.username,
            onValueChange = {
                viewModel.onEvent(
                    LoginEvent.UsernameChanged(it)
                )
            },
            label = "Username"
        )

        Spacer(
            modifier = Modifier.height(AppSpacing.md)
        )

        RcPasswordTextField(
            modifier = Modifier.fillMaxWidth(),

            value = state.password,

            onValueChange = {
                viewModel.onEvent(
                    LoginEvent.PasswordChanged(it)
                )
            },
            label = "Password",
            isPasswordVisible = state.isPasswordVisible,
            onVisibilityChange = {
                viewModel.onEvent(
                    LoginEvent.PasswordVisibilityChanged
                )
            },
            enabled = !state.isLoading,
            isError = false,
            supportingText = state.errorMessage,
        )

        Spacer(
            modifier = Modifier.height(AppSpacing.lg)
        )

        state.errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(
            modifier = Modifier.height(AppSpacing.lg)
        )

        RcPrimaryButton(
            label = "Login",
            onClick = {
                viewModel.onEvent(
                    LoginEvent.LoginClicked
                )
            },
            isLoading = state.isLoading,

            enabled = !state.isLoading
        )
    }
}