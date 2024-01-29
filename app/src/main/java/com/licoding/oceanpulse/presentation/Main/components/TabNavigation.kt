package com.licoding.oceanpulse.presentation.Main.components

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DonutLarge
import androidx.compose.material.icons.filled.DonutSmall
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.licoding.oceanpulse.data.models.BlogTabItem
import com.licoding.oceanpulse.presentation.common.RowIcon

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabNavigation(
    context: Context
){
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 4})

    val items = listOf(
        BlogTabItem(
            icon = Icons.Default.DonutSmall
        ),
        BlogTabItem(
            icon = Icons.Default.DonutSmall
        ),
        BlogTabItem(
            icon = Icons.Default.DonutSmall
        ),
        BlogTabItem(
            icon = Icons.Default.DonutSmall
        )
    )

    Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items.forEachIndexed {index,  item ->
                    RowIcon(index, pagerState, item.icon)
                }
            }

            HorizontalPager(state = pagerState) {page ->
                when(page) {
                    0 -> {
                        InfoTab(context)
                    }
                    1 -> {
                        PollutionTab()
                    }
                    2 -> {
                        MapView()
                    }
                    3-> {
                        Articles()
                    }
                }
            }
        }

}