package com.licoding.oceanpulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import com.example.compose.OceanPulseTheme

class RegisterActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OceanPulseTheme {
                Surface {
                    Text(
                        "Hello register"
                    )
                }
            }
        }
    }
}