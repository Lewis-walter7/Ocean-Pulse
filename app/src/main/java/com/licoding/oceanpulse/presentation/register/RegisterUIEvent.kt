package com.licoding.oceanpulse.presentation.register

sealed interface RegisterUIEvent {
    object OnloginButtonClicked: RegisterUIEvent
    object OnRegisterButtonClicked: RegisterUIEvent
    data class OnEmailChange(val email: String): RegisterUIEvent
    data class OnPasswordChange(val password: String): RegisterUIEvent
}