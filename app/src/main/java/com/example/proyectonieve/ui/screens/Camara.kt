package com.example.proyectonieve.ui.screens

import android.Manifest
import android.net.Uri
import android.widget.Toast
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.example.proyectonieve.data.crearArchivoImagen
import java.io.File

@Composable
fun CameraScreen() {

    val context = LocalContext.current
    var photoUri by remember { mutableStateOf<Uri?>(null) }

    val takePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            Toast.makeText(context, "Foto guardada en: $photoUri", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "No se tomó la foto", Toast.LENGTH_SHORT).show()
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            val file: File = crearArchivoImagen(context)
            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                file
            )
            photoUri = uri


            takePictureLauncher.launch(uri)

        } else {
            Toast.makeText(context, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            onClick = { permissionLauncher.launch(Manifest.permission.CAMERA) },
            modifier = Modifier
                .padding(bottom = 40.dp)
        ) {
            Text("Tomar foto")
        }
    }
}