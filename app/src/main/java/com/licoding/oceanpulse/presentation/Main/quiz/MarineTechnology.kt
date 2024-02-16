package com.licoding.oceanpulse.presentation.Main.quiz

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MarineTechnology() {
    Scaffold(
        topBar = {
            TopAppBar(
                title =
                {
                    Text("Marine Pollution")
                }
            )
        }
    ) {
        Text("Marine Pollution")
        Column {
            Text("Hey Mt")
        }
    }
}