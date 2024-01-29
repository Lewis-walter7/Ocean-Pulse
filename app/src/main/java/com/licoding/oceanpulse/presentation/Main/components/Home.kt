package com.licoding.oceanpulse.presentation.Main.components

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.DonutSmall
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.licoding.oceanpulse.data.models.BlogTabItem
import com.licoding.oceanpulse.presentation.common.MinimalDialog

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    context: Context
) {
    val interactionSource = MutableInteractionSource()
    val openAlertDialog = remember { mutableStateOf(false) }

    val onDismissRequest = {
        openAlertDialog.value = false
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Ocean Pulse"
                    )
                },
                actions = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = null
                        )
                    }

                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccessAlarm,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ){
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 75.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(10.dp))
                        .padding(10.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Did you know:",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Over 50% of oxygen on the planet comes from oceans."
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Read more",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        openAlertDialog.value = true
                                    },
                                    interactionSource = interactionSource,
                                    indication = null
                                ),
                            textAlign = TextAlign.End
                        )
                    }

                    if (openAlertDialog.value) {
                        MinimalDialog(onDismissRequest)
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                TabNavigation(context)
            }
        }
    }
}