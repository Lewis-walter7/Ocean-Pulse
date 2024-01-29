package com.licoding.oceanpulse.presentation.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)
@Composable
fun RowIcon(index: Int, pagerState: PagerState, icon: ImageVector) {
    var selectedState by remember {
        mutableIntStateOf(0)
    }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val scope = rememberCoroutineScope()

//    LaunchedEffect(selectedState) {
//        selectedState = index
//    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(screenWidth / 4)
    ) {
        IconButton(onClick = {
            scope.launch {
                pagerState.scrollToPage(index)
            }
            selectedState = index
            println(selectedState)
        }) {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        if (index == selectedState) {
            Divider(modifier = Modifier.width(screenWidth / 3f))
        }
    }
}