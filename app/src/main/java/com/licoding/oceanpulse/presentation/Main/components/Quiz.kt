package com.licoding.oceanpulse.presentation.Main.components

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.licoding.oceanpulse.presentation.Main.quiz.Category
import com.licoding.oceanpulse.presentation.Main.quiz.RewardBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Quiz(
    navController: NavController
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    val onDismissRequest = {
        showBottomSheet = false
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Categories"
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            showBottomSheet = !showBottomSheet
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        Category(
            navController
        )
        if (showBottomSheet){
            RewardBottomSheet(
                onDismissRequest = {
                    onDismissRequest()
                }
            )
        }
    }
}