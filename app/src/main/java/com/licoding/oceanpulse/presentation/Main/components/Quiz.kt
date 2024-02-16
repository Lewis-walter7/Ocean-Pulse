package com.licoding.oceanpulse.presentation.Main.components

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.licoding.oceanpulse.presentation.Main.quiz.Category

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Quiz(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Categories"
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick ={

                },
                content = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null
                    )
                }
            )
        }
    ) {
        Category(
            navController
        )
    }
}