package com.licoding.oceanpulse.presentation.Main.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.licoding.oceanpulse.data.constants.categories

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Category(
    navController: NavController
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    FlowRow(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 70.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        categories.map {quiz ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(screenWidth / 2.2f)
                    .height(120.dp)
                    .background(color = MaterialTheme.colorScheme.onSecondary, shape = RoundedCornerShape(10.dp))
                    .padding(20.dp)
                    .clickable {
                        navController.navigate((quiz.route))
                    },
            ) {
                Text(
                    text = quiz.name,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}