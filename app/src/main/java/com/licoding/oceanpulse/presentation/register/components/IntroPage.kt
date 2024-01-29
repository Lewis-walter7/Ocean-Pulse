package com.licoding.oceanpulse.presentation.register.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.licoding.oceanpulse.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroPage(
    navController: NavController
) {
    val pagerState = rememberPagerState(pageCount = {4}, initialPage = 0)
    val scope = rememberCoroutineScope()
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            HorizontalPager(
                state = pagerState
            ) { page ->
                when (page) {
                    0 -> {
                        Page1(pagerState, scope, navController)
                     }
                    1 -> {
                        Page2(pagerState, scope, navController)
                    }
                    2 -> {
                        Page3(pagerState, scope, navController)
                    }
                    3 -> {
                        Page4(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Page4(navController: NavController) {
    Column {
        AsyncImage(
            model = R.drawable.oceanplant,
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(9 / 12f)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Gently swaying with the rhythm of the currents, the aquatic plant serves as a verdant guardian beneath the water's surface, contributing to the health and balance of marine ecosystems with its vital role in oxygen production and habitat support.",
            textAlign = TextAlign.Center,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navController.navigate("login")
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Become a member")
        }
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Page3(pagerState: PagerState, scope: CoroutineScope, navController: NavController) {
    Column {
        AsyncImage(
            model = R.drawable.oceanbeach,
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(9 / 12f)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Stretching along the shoreline, the pristine beach unveils a serene sanctuary where golden sands meet crystal-clear waters, embodying the natural harmony that clean and untouched coastal environments bring to both land and sea.",
            textAlign = TextAlign.Center,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    navController.navigate("login")
                }
            ) {
                Text(
                    text = "Skip",
                    fontSize = 16.sp
                )
            }
            Button(
                onClick = {
                    scope.launch {
                        pagerState.scrollToPage(4)
                    }
                }
            ) {
                Text(
                    text = "Next",
                    fontSize = 16.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Page2(pagerState: PagerState, scope: CoroutineScope, navController: NavController) {
    Column {
        AsyncImage(
            model = R.drawable.oceanjellyfish,
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(9 / 12f)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Intricately pulsating through the azure waters, the ethereal jellyfish exemplifies the delicate beauty and adaptability of marine life beneath the ocean's surface.",
            textAlign = TextAlign.Center,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    navController.navigate("login")
                }
            ) {
                Text(
                    text = "Skip",
                    fontSize = 16.sp
                )
            }
            Button(
                onClick = {
                    scope.launch {
                        pagerState.scrollToPage(3)
                    }
                }
            ) {
                Text(
                    text = "Next",
                    fontSize = 16.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Page1(pagerState: PagerState, scope: CoroutineScope, navController: NavController) {
    Column {
        AsyncImage(
            model = R.drawable.oceanturtle,
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(9 / 12f)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Elegantly gliding through the ocean depths, the resilient sea turtle symbolizes the grace and endurance of marine life in the face of environmental challenges.",
            textAlign = TextAlign.Center,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    navController.navigate("login")
                }
            ) {
                Text(
                    text = "Skip",
                    fontSize = 16.sp
                )
            }
            Button(
                onClick = {
                    scope.launch {
                        pagerState.scrollToPage(2)
                    }
                }
            ) {
                Text(
                    text = "Next",
                    fontSize = 16.sp
                )
            }
        }
    }
}
