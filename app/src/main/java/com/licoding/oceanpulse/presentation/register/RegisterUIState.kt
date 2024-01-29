package com.licoding.oceanpulse.presentation.register

import com.licoding.oceanpulse.data.models.User

data class RegisterUIState(
    val registerEmail:String? = null,
    val loginEmail:String? = null,
    val registerPassword: String? = null,
    val loginPassword: String? = null,
    val currentUser: User? = null,
    val name: String? = null
)