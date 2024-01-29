package com.licoding.oceanpulse.presentation.upload

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UploadViewModel: ViewModel() {

    private val _state = MutableStateFlow(UploadUIState())
    val state = _state.asStateFlow()
    fun onEvent(event:UploadUIEvent) {
        when(event){
            is UploadUIEvent.OnSelecteduriChange -> {
                _state.update {
                    it.copy(
                        selectedUri = event.uri
                    )
                }
            }

            UploadUIEvent.OnClearSelectedUri -> {
                _state.update {
                    it.copy(
                        selectedUri = null
                    )
                }
            }
        }
    }
}