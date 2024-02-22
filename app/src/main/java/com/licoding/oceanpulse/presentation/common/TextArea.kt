package com.licoding.oceanpulse.presentation.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.licoding.oceanpulse.presentation.upload.UploadUIEvent
import com.licoding.oceanpulse.presentation.upload.UploadUIState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextArea(
    onEvent: (UploadUIEvent) -> Unit,
    state: UploadUIState
) {
    BasicTextField2(
        value = state.body ?: "",
        onValueChange = {
            onEvent(UploadUIEvent.OnBodyChange(it))
        },
//        lineLimits = ,
//        decorationBox = {
//            if(state.body?.isEmpty() == true) {
//                Text(
//                    text = "What are you going to talk about?"
//                )
//            }
//        },
        modifier = Modifier.fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(5.dp))
            .padding(10.dp)
    )
}
