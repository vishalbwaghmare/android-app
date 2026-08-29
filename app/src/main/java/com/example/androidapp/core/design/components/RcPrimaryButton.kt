package com.example.androidapp.core.design.components

import AppColor
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RcPrimaryButton(
    label : String,
    onClick : ()-> Unit,
    modifier : Modifier = Modifier,
    enabled : Boolean = true,
    isLoading : Boolean = false
){

    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),

        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = AppColor.White,
            containerColor = AppColor.Primary,
            disabledContainerColor = AppColor.Surface
        ),
        enabled = enabled && !isLoading,

        onClick = onClick,

    ) {
        if(isLoading){
            CircularProgressIndicator(
                modifier = Modifier.height(20.dp)
            )
        } else{
            Text(label)
        }
    }
}