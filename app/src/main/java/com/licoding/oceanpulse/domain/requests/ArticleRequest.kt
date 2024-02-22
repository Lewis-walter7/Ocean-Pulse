package com.licoding.oceanpulse.domain.requests

import android.net.Uri

data class ArticleRequest(
    val title: String,
    val selectedUri: Uri? = null,
    val about: String
)
