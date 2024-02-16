package com.licoding.oceanpulse.presentation.Main.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
            .padding(top = 70.dp, start = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        categories.map {quiz ->
            Text(
                text = quiz.name,
                modifier = Modifier
                    .width(screenWidth / 2.2f)
                    .height(120.dp)
                    .background(color = MaterialTheme.colorScheme.onSecondary, shape = RoundedCornerShape(10.dp))
                    .padding(20.dp)
                    .clickable {
                        navController.navigate((quiz.route))
                    },
                textAlign = TextAlign.Center
            )
        }
    }
}