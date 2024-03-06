package com.licoding.oceanpulse.presentation.Main.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ModeComment
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.licoding.oceanpulse.data.remote.userRepo.getUserById
import com.licoding.oceanpulse.domain.models.Article
import com.licoding.oceanpulse.domain.models.User
import kotlinx.coroutines.launch

@Composable
fun ArticleCard(article: Article) {
    val scope = rememberCoroutineScope()
    var user: User? = null
    Column {
        scope.launch {
            user = getUserById(article.userId.toString())
        }
        AsyncImage(
            model = article.image,
            contentDescription = "image",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = null
                    )
                }
                Spacer(
                    modifier = Modifier.width(10.dp)
                )
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ModeComment,
                        contentDescription = null
                    )
                }
                Spacer(
                    modifier = Modifier.width(10.dp)
                )
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.Send,
                        contentDescription = null,
                        modifier = Modifier
                            .rotate(-45f)
                    )
                }
            }
            Text(
                text = "1190 likes",
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = user?.name ?: "",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )
            Text(
                text = article.body
            )
        }
    }
}