package com.licoding.oceanpulse.presentation.Main.home.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.licoding.oceanpulse.domain.models.Article

@Composable
fun ArticleCard(article: Article) {
    Text(
        text = article.body
    )
}