package com.licoding.oceanpulse

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.OceanPulseTheme
import com.google.firebase.auth.FirebaseAuth
import com.licoding.oceanpulse.domain.FirebaseService
import com.licoding.oceanpulse.presentation.register.RegisterViewModel
import com.licoding.oceanpulse.presentation.register.components.IntroPage
import com.licoding.oceanpulse.presentation.register.components.LoginPage
import com.licoding.oceanpulse.presentation.register.components.RegisterPage

class RegisterActivity: ComponentActivity() {
    private val auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        val navigate = {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        if (auth.currentUser != null) {
            navigate()
        }
         val firebaseService = FirebaseService(application, navigate)
         val viewModel by viewModels<RegisterViewModel>(
            factoryProducer = {
                object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return RegisterViewModel(firebaseService) as T
                    }
                }
            }
        )
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        setContent {
            OceanPulseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "intropage") {
                        composable("intropage") {
                            IntroPage(navController = navController)
                        }
                        composable("login") {
                            LoginPage(navController = navController, onEvent = viewModel::onEvent)
                        }
                        composable("register") {
                            RegisterPage(
                                onEvent = viewModel::onEvent,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}