package com.licoding.oceanpulse.presentation.register

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.licoding.oceanpulse.data.models.User
import com.licoding.oceanpulse.domain.FirebaseService
import com.licoding.oceanpulse.domain.requests.AuthRequest
import com.licoding.oceanpulse.domain.room.DatabaseSingleton
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val firebaseService: FirebaseService,
    private val application: Application
): ViewModel() {
    private val appDatabase = DatabaseSingleton.getDatabase(application)
    var user: User? = null
    init {
        viewModelScope.launch {
            delay(3000)
            _isLoading.value = false
            user = appDatabase.userDao.getUser()
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
                    email = state.value.registerEmail!!,
                    password = state.value.registerPassword!!,
                    name = state.value.name
                )
                firebaseService.createUser(authRequest, appDatabase)
            }
            RegisterUIEvent.OnloginButtonClicked -> {
                val authRequest = AuthRequest(
                    email = state.value.loginEmail!!,
                    password = state.value.loginPassword!!
                )
                firebaseService.signIn(authRequest, appDatabase)
            }
            is RegisterUIEvent.OnRegisterEmailChange -> {
                _state.update {
                    it.copy(
                        registerEmail = event.email
                    )
                }
            }
            is RegisterUIEvent.OnRegisterPasswordChange -> {
                _state.update {
                    it.copy(
                        registerPassword = event.password
                    )
                }
            }

            is RegisterUIEvent.OnNameChange -> {
                _state.update {
                    it.copy(
                        name = event.name
                    )
                }
            }

            is RegisterUIEvent.OnLoginEmailChange -> {
                _state.update {
                    it.copy(
                        loginEmail = event.email
                    )
                }
            }

            is RegisterUIEvent.OnLoginPasswordChange -> {
                _state.update {
                    it.copy(
                        loginPassword = event.password
                    )
                }
            }
        }
    }
}