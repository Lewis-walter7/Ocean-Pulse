package com.licoding.oceanpulse.presentation.Main.blog

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.licoding.oceanpulse.domain.constants.BlogConstants
import com.licoding.oceanpulse.domain.response.BlogResponse
import com.licoding.oceanpulse.domain.utils.toBlog

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SetJavaScriptEnabled", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Blog(
    onNavigate: (String) -> Unit,
    blogs: List<BlogResponse>
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Popular Blogs"
                    )
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(top = 70.dp, start = 15.dp, end = 15.dp, bottom = 70.dp)
        ) {
            items(blogs) {item ->
                BlogCard(
                    onNavigate,
                    item.toBlog()
                )
            }
        }
    }
}