package com.sousa.location_project.utils

import android.content.Context
import android.graphics.Bitmap
import java.io.File
import java.util.UUID

fun saveToInternalStorage(context: Context, bitmapImage: Bitmap): String {
    val name = "thumbnail + ${UUID.randomUUID()}.jpg}"
    context.openFileOutput(name, Context.MODE_PRIVATE).use { fos ->
        bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos)
    }
    return name
}

fun getImageFromInternalStorage(context: Context, imageFileName: String): String? {
    val directory = context.filesDir
    val file = File(directory, imageFileName)
    return file.path
}