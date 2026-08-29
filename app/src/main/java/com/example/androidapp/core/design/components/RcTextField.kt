package com.example.androidapp.core.design.components
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androidapp.core.design.AppShapes

@Composable
fun RcTextFiled(
    value : String,
    onValueChange: (String) -> Unit,
    label : String,
    modifier : Modifier = Modifier,
    enabled : Boolean = true,
    isError: Boolean = false,
    supportingText : String ?= null

){

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        shape = AppShapes.medium,

        value = value,

        onValueChange = onValueChange,

        enabled = enabled,

        label = {
            Text(label)
        },
        isError = isError,

        supportingText = supportingText?.let {message -> {
            Text(message)
        }}
    )
}