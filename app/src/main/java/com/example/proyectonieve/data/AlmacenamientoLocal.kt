package com.example.proyectonieve.data

import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import android.os.Environment
import android.content.Context

@Throws(IOException::class)
fun crearArchivoImagen(context: Context): File {
    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val imageFileName = "JPEG_${timeStamp}_"

    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

    return File.createTempFile(
        imageFileName,
        ".jpg",
        storageDir
    )
}