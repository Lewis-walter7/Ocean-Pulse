package com.licoding.oceanpulse.presentation.Main.components

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.licoding.oceanpulse.presentation.common.TextArea
import com.licoding.oceanpulse.presentation.upload.UploadUIEvent
import com.licoding.oceanpulse.presentation.upload.UploadUIState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Post(
    navigate: () -> Unit,
    onEvent: (UploadUIEvent) -> Unit,
    state: UploadUIState
) {
    var shouldFocus by remember { mutableStateOf(true) }
    val focusRequester = remember { FocusRequester() }

    val currentView = LocalView.current
    LaunchedEffect(currentView) {
        if(shouldFocus) {
            focusRequester.requestFocus()
            shouldFocus = false
        }
    }
    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            onEvent(UploadUIEvent.OnSelecteduriChange(uri))
        }
    )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "New Article"
                    )
                },
                actions = {
                    TextButton(
                        onClick = {
                            onEvent(UploadUIEvent.OnPostButtonClicked)
                        },
                        enabled = state.title != null
                    ) {
                        Text(
                            text = "Post"
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navigate()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(top = 70.dp, start = 16.dp, end = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .imePadding()
            ) {
                TextField(
                    value = state.title ?: "",
                    onValueChange = {
                        onEvent(UploadUIEvent.OnTitleChange(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    placeholder = {
                        Text(
                            text = "Title"
                        )
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextArea(onEvent)
                Spacer(modifier = Modifier.height(10.dp))
                if (state.selectedUri != null) {
                    Box(
                        contentAlignment = Alignment.TopEnd
                    ) {
                        AsyncImage(
                            model = state.selectedUri,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(7.dp))
                                .aspectRatio(1f),
                            contentScale = ContentScale.Crop
                        )
                        IconButton(
                            onClick = {
                                onEvent(UploadUIEvent.OnClearSelectedUri)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = null,
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.onBackground, shape = CircleShape)
                                    .padding(3.dp),
                                tint = MaterialTheme.colorScheme.background
                            )
                        }
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
                    .padding(end = 16.dp, bottom = 16.dp)
            ) {
                IconButton(
                    onClick = {
                        singlePhotoPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    }
                ){
                    Icon(
                        imageVector = Icons.Default.PhotoLibrary,
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                IconButton(
                    onClick = {
                        //singlePhotoPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    }
                ){
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                }
            }
        }

    }
}