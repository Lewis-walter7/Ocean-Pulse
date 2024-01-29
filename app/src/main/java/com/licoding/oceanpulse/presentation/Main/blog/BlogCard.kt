package com.licoding.oceanpulse.presentation.Main.blog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.licoding.oceanpulse.data.models.Blog

@Composable
fun BlogCard(
    onNavigate: (String) -> Unit,
    blog: Blog
) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(10.dp))
            .clickable {
                onNavigate(blog.url)
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            AsyncImage(
                model = blog.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
                contentScale = ContentScale.FillBounds
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 10.dp, top = 5.dp)
        ) {
            Text(
                text = blog.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 17.sp
            )
            Text(
                text = blog.description,
                fontSize = 13.sp,
                fontWeight = FontWeight.W400,
                modifier = Modifier
                    .padding(bottom = 5.dp)
            )
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}