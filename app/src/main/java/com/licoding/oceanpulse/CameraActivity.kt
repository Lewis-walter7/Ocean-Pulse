package com.licoding.oceanpulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.licoding.oceanpulse.presentation.Main.camera.Camera
import com.licoding.oceanpulse.presentation.Main.camera.CameraViewModel

class CameraActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel by viewModels<CameraViewModel>()
            val bitmaps by viewModel.bitmaps.collectAsState()
            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.fillMaxSize()
            ) {
                Camera(
                    bitmaps = bitmaps,
                    onPhotoTaken = viewModel::updateBitmaps
                )
            }
        }
    }
}