package com.licoding.oceanpulse.presentation.common

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import com.licoding.oceanpulse.R
import com.licoding.oceanpulse.domain.models.User

@Composable
fun UserPhoto(size: Dp, user: User) {
    AsyncImage(
        model = user.profileImage ?: R.drawable.placeholderavatar,
        contentDescription = null,
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(50))
    )
}