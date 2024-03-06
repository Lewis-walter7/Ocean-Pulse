package com.licoding.oceanpulse.presentation.Main.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import com.licoding.oceanpulse.domain.models.Article


@Composable
fun ArticleSection(articles: List<Article>) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        if(articles.isEmpty()) {
            Column {
                Text(
                    text = "Upload your first article"
                )

                ClickableText(
                    text = AnnotatedString("Create your first article"),
                    onClick = {

                    }
                )
            }
        } else {
            Column {

            }
        }
    }
}

