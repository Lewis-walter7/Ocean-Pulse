package com.licoding.oceanpulse.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.licoding.oceanpulse.domain.FirebaseService
import com.licoding.oceanpulse.domain.requests.AuthRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    val firebaseService: FirebaseService
): ViewModel() {
    init {
        viewModelScope.launch {
            delay(3000)
            _isLoading.value = false
        }
    }

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _state = MutableStateFlow(RegisterUIState())
    val state = _state.asStateFlow()

    fun onEvent(event: RegisterUIEvent) {
        when(event) {
            RegisterUIEvent.OnRegisterButtonClicked -> {
                val authRequest = AuthRequest(
                    email = state.value.email!!,
                    password = state.value.password!!
                )
                firebaseService.createUser(authRequest)
            }
            RegisterUIEvent.OnloginButtonClicked -> {
                val authRequest = AuthRequest(
                    email = state.value.email!!,
                    password = state.value.password!!
                )
                firebaseService.signIn(authRequest)
            }
            is RegisterUIEvent.OnEmailChange -> {
                _state.update {
                    it.copy(
                        email = event.email
                    )
                }
            }
            is RegisterUIEvent.OnPasswordChange -> {
                _state.update {
                    it.copy(
                        password = event.password
                    )
                }
            }
        }
    }
}