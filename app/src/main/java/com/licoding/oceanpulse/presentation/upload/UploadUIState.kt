package com.licoding.oceanpulse.presentation.upload

import android.net.Uri

data class UploadUIState(
    val selectedUri: Uri? = null,
    val title : String? = null,
    val body: String? = null,
)