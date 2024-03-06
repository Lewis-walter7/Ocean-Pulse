package com.licoding.oceanpulse.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val userId: String,
    val email: String,
    val bio: String? = null,
    val profileImage: String? = null,
    val name: String,
    val createdAt: Long
)
