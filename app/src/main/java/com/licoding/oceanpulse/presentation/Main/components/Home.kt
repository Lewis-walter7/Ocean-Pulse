package com.licoding.oceanpulse.presentation.Main.components

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.licoding.oceanpulse.domain.models.Article
import com.licoding.oceanpulse.presentation.Main.home.components.ArticleCard
import com.licoding.oceanpulse.presentation.Main.home.components.Choice

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    context: Context,
    navController: NavController,
    articles: List<Article>
) {
    var openAlertDialog by remember { mutableStateOf(false) }

    val onDismissRequest = {
        openAlertDialog = false
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Ocean Pulse"
                    )
                },
                actions = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = null
                        )
                    }

                    IconButton(
                        onClick = {
                            navController.navigate("search")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(top = 73.dp)
        ){
            Choice(navController)
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn {
                items(articles) {article ->
                    ArticleCard(article)
                }
            }
        }
    }
}