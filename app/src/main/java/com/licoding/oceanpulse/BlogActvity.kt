package com.licoding.oceanpulse

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.compose.OceanPulseTheme

class BlogActivity: ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("SetJavaScriptEnabled", "UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url = intent.getStringExtra("url")!!
        setContent { 
            OceanPulseTheme { 
                Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Column {
                                        Text(
                                            text = "google.com",
                                            fontSize = 12.sp
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))

                                        Text(
                                            text = url,
                                            maxLines = 1,
                                            fontSize = 10.sp
                                        )
                                    }
                                },
                                navigationIcon = {
                                    IconButton(
                                        onClick = {
                                            startActivity(Intent(this@BlogActivity, MainActivity::class.java))
                                            finish()
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = null
                                        )
                                    }
                                }
                            )
                        }
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(top = 70.dp)
                        ) {
                            //Spacer(modifier = Modifier.height(70.dp))
                            AndroidView(
                                factory = { context ->
                                    WebView(context).apply {
                                        layoutParams = ViewGroup.LayoutParams(
                                            ViewGroup.LayoutParams.MATCH_PARENT,
                                            ViewGroup.LayoutParams.MATCH_PARENT
                                        )
                                        settings.javaScriptEnabled = true
                                        webChromeClient = WebChromeClient()
                                        webViewClient = WebViewClient()
                                        loadUrl(url)
                                    }
                                },
                                update = {
                                    it.loadUrl(url)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}