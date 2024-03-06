package com.licoding.oceanpulse.presentation.Main.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.ViewWeek
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                        text = "Following",
                        fontSize = 13.sp
                    )
                }
                VerticalDivider(modifier = Modifier.height(30.dp).width(1.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "98",
                    )
                    Text(
                        text = "Followers",
                        fontSize = 13.sp
                    )
                }
                VerticalDivider(modifier = Modifier.height(30.dp).width(1.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "98",
                    )
                    Text(
                        text = "Likes",
                        fontSize = 13.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                TextButton(
                    onClick = {

                    },
                    modifier = Modifier
                        .width(150.dp)
                        .background(shape = RoundedCornerShape(10), color = MaterialTheme.colorScheme.onSurface)
                ) {
                    Text(
                        text = "Edit profile",
                        color = MaterialTheme.colorScheme.surface,
                        fontSize = 17.sp
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                IconButton(
                    onClick = {

                    },
                    modifier = Modifier
                        .background(Color.LightGray, shape = RoundedCornerShape(10))
                ) {
                    Icon(
                        imageVector = Icons.Default.PersonAdd,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.surface
                    )
                }
            }
            if (state.user?.bio != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = state.user.bio,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ViewWeek,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }

                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}