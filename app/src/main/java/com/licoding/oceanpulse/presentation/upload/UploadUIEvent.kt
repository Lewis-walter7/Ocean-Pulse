package com.licoding.oceanpulse.presentation.upload

import android.net.Uri

sealed interface UploadUIEvent {
    data class OnSelecteduriChange(val uri: Uri?): UploadUIEvent
    data class OnBodyChange(val body: String): UploadUIEvent
    data class OnTitleChange(val title: String): UploadUIEvent
    data object OnClearSelectedUri: UploadUIEvent
    data object OnPostButtonClicked: UploadUIEvent
}
