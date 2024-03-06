package com.licoding.oceanpulse.domain.utils

import android.content.Context
import android.net.Uri
import android.os.Environment
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

object FileUtil {
    private const val DIRECTORY_NAME = "Oceanpulse"

    fun bitmapToUriQ(context: Context): Uri? {
        val directory = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), DIRECTORY_NAME)
        if (!directory.exists()) {
            directory.mkdirs()
        }

        val imageFile = File(directory, "${UUID.randomUUID()}.png")

        return Uri.fromFile(imageFile)
    }

    fun bitmapToUri(context: Context): Uri? {
        val directory = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), DIRECTORY_NAME)
        if (!directory.exists()) {
            directory.mkdirs()
        }

        val imageFile = File(directory, "${UUID.randomUUID()}.png")

        return Uri.fromFile(imageFile)
    }

    fun createImageFile(directory: File): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileName = "IMG_$timestamp.jpg"
        return File(directory, fileName)
    }
}