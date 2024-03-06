package com.licoding.oceanpulse.presentation.Main.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.licoding.oceanpulse.domain.models.ChoiceModel

@Composable
fun Choice(navController: NavController) {
    val texts = listOf(
        ChoiceModel(
            text = "For you",
            route = "foryou"
        ),
        ChoiceModel(
            text = "Following",
            route = "following"
        )
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        texts.forEachIndexed { index, choice ->
            text(
                index = index,
                selectedIndex = selectedIndex,
                choiceModel = choice,
                onClick = {
                    selectedIndex = index
                }
            )
        }
    }
}

@Composable
fun text(index: Int, selectedIndex: Int, choiceModel: ChoiceModel, onClick: () -> Unit) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Column {
        Text(
            text = choiceModel.text,
            fontSize = 16.sp,
            modifier = Modifier
                .width(screenWidth / 2)
                .clickable {
                    onClick()
                },
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        HorizontalDivider(
            thickness = 2.dp,
            color = if (index == selectedIndex) MaterialTheme.colorScheme.onBackground else Color.Gray,
            modifier = Modifier
                .width(screenWidth / 2)
        )
    }
}