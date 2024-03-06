package com.licoding.oceanpulse.presentation.Main.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun Header(navController: NavController){
    var value by remember {
        mutableStateOf("")
    }
    BasicTextField(
        onValueChange = {
            value = it
        },
        value = value,
        decorationBox = {innerTextField ->
            if(value.isEmpty()) {
                Text(
                    text = "Search"
                )
            }
            innerTextField()
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onSurfaceVariant, RoundedCornerShape(6.dp))
            .padding(5.dp),
        textStyle = TextStyle(
            fontSize = 16.sp,
        )
    )
}