package com.licoding.oceanpulse.presentation.upload

import android.net.Uri

sealed interface UploadUIEvent {
    data class OnSelecteduriChange(val uri: Uri?): UploadUIEvent
    object OnClearSelectedUri: UploadUIEvent
}
