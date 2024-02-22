package com.licoding.oceanpulse.presentation.Main

import com.licoding.oceanpulse.data.models.Fact
import com.licoding.oceanpulse.data.models.User


data class MainUIState(
    val user: User? = null,
    val selectedLevelIndex: Int = 0,
    val fact: Fact? = null,
)