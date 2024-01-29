package com.licoding.oceanpulse.domain.response

import kotlinx.serialization.Serializable

@Serializable
data class BlogResponse(
    val url: String,
    val title: String,
    val description: String,
    val image: String
)
