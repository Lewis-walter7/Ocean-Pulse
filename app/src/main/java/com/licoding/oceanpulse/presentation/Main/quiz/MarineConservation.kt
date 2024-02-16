package com.licoding.oceanpulse.presentation.Main.quiz

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarineConservation(){
    Scaffold(
        topBar = {
            TopAppBar(
                title =
                {
                    Text("Marine Conservation")
                }
            )
        }
    ) {
        Text("Marine Pollution")
    }
}