package com.example.androidapp.core.design.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.androidapp.core.design.AppShapes

@Composable
fun RcPasswordTextField(
    value : String,
    onValueChange : (String) -> Unit,
    label : String,
    isPasswordVisible: Boolean,
    onVisibilityChange : ()-> Unit,
    modifier : Modifier = Modifier,
    enabled : Boolean = true,
    isError: Boolean = false,
    supportingText : String? = null

){

    OutlinedTextField(
        modifier = modifier,
        shape = AppShapes.medium,
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,

        label = {
            Text(label)
        },

        singleLine = true,

        isError = isError,

        visualTransformation =
            if(isPasswordVisible){
                VisualTransformation.None
            } else{
                PasswordVisualTransformation()
        },

        trailingIcon = {
            IconButton(
                onClick = onVisibilityChange
            ) {
                Icon(
                    imageVector =
                        if (isPasswordVisible) {
                            Icons.Default.VisibilityOff
                        } else {
                            Icons.Default.Visibility
                        },

                    contentDescription =
                        if (isPasswordVisible) {
                            "Hide password"
                        } else {
                            "Show password"
                        }
                )
            }
        },

        supportingText = supportingText?.let {message -> {
            Text(
                message
            )
        }}

    )
}