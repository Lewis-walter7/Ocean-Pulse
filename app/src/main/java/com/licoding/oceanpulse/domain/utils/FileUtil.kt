package com.licoding.oceanpulse.domain.utils

import android.content.Context
import android.os.Environment
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

object FileUtil {
    private const val DIRECTORY_NAME = "oceanpulse"

    fun createAppDirectory(context: Context): File {
        val directory = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), DIRECTORY_NAME)
        if (!directory.exists()) {
            directory.mkdirs()
        }
        return directory
    }

    fun createImageFile(directory: File): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileName = "IMG_$timestamp.jpg"
        return File(directory, fileName)
    }
}