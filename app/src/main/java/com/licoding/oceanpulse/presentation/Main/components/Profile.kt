package com.licoding.oceanpulse.presentation.Main.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.licoding.oceanpulse.data.models.User
import com.licoding.oceanpulse.presentation.Main.MainUIEvent
import com.licoding.oceanpulse.presentation.Main.MainUIState
import com.licoding.oceanpulse.presentation.common.UserPhoto

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(
    navigate: () -> Unit,
    onNavigate: () -> Unit,
    state: MainUIState,
    onEvent: (MainUIEvent) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    state.user?.let {
                        Text(
                            text = it.name
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            onNavigate()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Camera,
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                        )
                    }
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(top = 70.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                UserPhoto(100.dp, state.user!!)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 40.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "98",
                    )
                    Text(
                        text = "Following"
                    )
                }
                Divider(modifier = Modifier.height(30.dp).width(1.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "98",
                    )
                    Text(
                        text = "Followers"
                    )
                }
                Divider(modifier = Modifier.height(30.dp).width(1.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "98",
                    )
                    Text(
                        text = "Following"
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextButton(
                    onClick = {

                    },
                    modifier = Modifier
                        .background(shape = RoundedCornerShape(10), color = MaterialTheme.colorScheme.secondary)
                ) {
                    Text(
                        text = "Edit profile"
                    )
                }
            }

            Text(
                "Profile",
                modifier = Modifier.clickable {
                    Firebase.auth.signOut()
                    navigate()
                    onEvent(MainUIEvent.OnLogOutButtonClicked)
                }
            )
        }
    }
}