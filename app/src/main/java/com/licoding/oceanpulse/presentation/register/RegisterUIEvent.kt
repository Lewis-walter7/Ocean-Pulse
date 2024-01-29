package com.licoding.oceanpulse.presentation.register

sealed interface RegisterUIEvent {
    object OnloginButtonClicked: RegisterUIEvent
    object OnRegisterButtonClicked: RegisterUIEvent
    data class OnRegisterEmailChange(val email: String): RegisterUIEvent
    data class OnLoginEmailChange(val email: String): RegisterUIEvent
    data class OnLoginPasswordChange(val password: String): RegisterUIEvent
    data class OnRegisterPasswordChange(val password: String): RegisterUIEvent
    data class OnNameChange(val name: String): RegisterUIEvent
}