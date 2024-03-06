package com.licoding.oceanpulse.domain.models

data class Article(
    val id: String? = null,
    val userId: String? = null,
    val title: String,
    val body: String,
    val image: String? = null,
    val createdAt: Long? = 0
)
