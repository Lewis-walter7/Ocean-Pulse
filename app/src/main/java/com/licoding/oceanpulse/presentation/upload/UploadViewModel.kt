package com.licoding.oceanpulse.presentation.upload

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.licoding.oceanpulse.data.models.Article
import com.licoding.oceanpulse.domain.services.FirebaseDocumentServices
import com.licoding.oceanpulse.domain.services.UploadFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class UploadViewModel(
    private val application: Application
): ViewModel() {

    private val uploadService = FirebaseDocumentServices()
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

            UploadUIEvent.OnPostButtonClicked -> {
                viewModelScope.launch(Dispatchers.IO) {
                    if (state.value.selectedUri != null) {
                        val url = UploadFile(state.value.selectedUri, application)
                        val newArticle = Article(
                            image = url.toString(),
                            title = state.value.title!!,
                            body = state.value.body!!,
                            id = UUID.randomUUID().toString()
                        )
                        uploadService.createPostDocument(newArticle)
                    } else {
                        val newArticle = Article(
                            title = state.value.title!!,
                            body = state.value.body!!,
                            id = UUID.randomUUID().toString()
                        )
                        uploadService.createPostDocument(newArticle)
                    }
                }
            }

            is UploadUIEvent.OnBodyChange -> {
                _state.update {
                    it.copy(
                        body = event.body
                    )
                }
            }
            is UploadUIEvent.OnTitleChange -> {
                _state.update {
                    it.copy(
                        title = event.title
                    )
                }
            }
        }
    }
}