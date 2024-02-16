package com.licoding.oceanpulse.presentation.Main.camera

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture.OnImageCapturedCallback
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Camera(
    bitmaps: List<Bitmap>,
    onPhotoTaken: (Bitmap) -> Unit
) {
    val bottomSheetState = rememberBottomSheetScaffoldState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE or
                CameraController.VIDEO_CAPTURE
            )
        }
    }
    BottomSheetScaffold(
        scaffoldState = bottomSheetState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            PhotoBottomSheet(bitmaps)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues)
        ) {
            CameraPreview(
                controller = controller,
                modifier = Modifier
                    .fillMaxSize()
            )

            IconButton(
                onClick = {
                    controller.cameraSelector = if(controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                        CameraSelector.DEFAULT_FRONT_CAMERA
                    } else {
                        CameraSelector.DEFAULT_BACK_CAMERA
                    }
                },
                modifier = Modifier
                    .offset(16.dp, 16.dp)
            ){
                Icon(
                    imageVector = Icons.Default.Cameraswitch,
                    contentDescription = "Camera Switch"
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 16.dp, vertical = 25.dp),
                horizontalArrangement = Arrangement.SpaceAround,
            ){
                IconButton(
                    onClick = {
                        scope.launch {
                            bottomSheetState.bottomSheetState.expand()
                        }
                    },
                ){
                    Icon(
                        imageVector = Icons.Default.PhotoLibrary,
                        contentDescription = "Camera Switch"
                    )
                }

                IconButton(
                    onClick = {
                        takePhoto(
                            controller = controller,
                            onPhotoTaken = onPhotoTaken,
                            context = context
                        )
                    },
                    modifier = Modifier
                        .padding(4.dp)
                        .background(MaterialTheme.colorScheme.onBackground, shape = RoundedCornerShape(50))
                ){
                    Icon(
                        imageVector = Icons.Default.Circle,
                        contentDescription = "Camera Switch",
                        modifier = Modifier
                            .size(30.dp)
                    )
                }
            }
        }
    }
}


private fun takePhoto(
    controller: LifecycleCameraController,
    onPhotoTaken: (Bitmap) -> Unit,
    context: Context
) {
    controller.takePicture(
        ContextCompat.getMainExecutor(context),
        object : OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                super.onCaptureSuccess(image)
                val matrix = Matrix().apply {
                    if (controller.cameraSelector == CameraSelector.DEFAULT_FRONT_CAMERA) {
                        postScale(-1f, 1f)
                    }
                }

                val bitmap = Bitmap.createBitmap(
                    image.toBitmap(),
                    0,
                    0,
                    image.width,
                    image.height,
                    matrix,
                    true
                )
                onPhotoTaken(bitmap)
            }

            override fun onError(exception: ImageCaptureException) {
                super.onError(exception)
                Log.e("CAMERA EXCEPTION", "Failed to ake a photo")
            }
        }
    )
}