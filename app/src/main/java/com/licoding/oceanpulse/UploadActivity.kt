package com.licoding.oceanpulse

import android.content.Intent
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.OceanPulseTheme
import com.licoding.oceanpulse.presentation.Main.components.Post
import com.licoding.oceanpulse.presentation.upload.UploadViewModel

class UploadActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel by viewModels<UploadViewModel>(
            factoryProducer = {
                object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return UploadViewModel(application) as T
                    }
                }
            }
        )
        super.onCreate(savedInstanceState)
        val navigate = {
            startActivity(Intent(this@UploadActivity, MainActivity::class.java))
            finish()
        }

        setContent {
            val state by viewModel.state.collectAsState()
            val navController = rememberNavController()
            OceanPulseTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "post"
                    ) {
                        composable("post"){
                            Post(
                                navigate = {
                                    navigate()
                                },
                                onEvent = viewModel::onEvent,
                                state = state
                            )
                        }
                    }
                }
            }
        }
    }
}