package com.licoding.oceanpulse.presentation.Main.quiz

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.licoding.oceanpulse.data.constants.quizes
import com.licoding.oceanpulse.data.models.Quiz
import com.licoding.oceanpulse.presentation.Main.MainUIEvent
import com.licoding.oceanpulse.presentation.Main.MainUIState

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MarinePollution(
    navController: NavController,
    onEvent: (MainUIEvent) -> Unit,
    state: MainUIState
) {
    var selectedQuizIndex by remember {
        mutableIntStateOf(0)
    }
    val selectedLevel = quizes[state.selectedLevelIndex]
    Scaffold(
        topBar = {
            TopAppBar(
                title =
                {
                    Text("Marine Pollution")
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 70.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Complete the quizes below to earn points"
                )
            }
                MenuBox(onEvent)
            Spacer(modifier = Modifier.height(16.dp))
            QuizCard(selectedLevel.quizes[selectedQuizIndex])
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = {
                    },
                ) {
                    Text(
                        text = "Quit"
                    )
                }
                Button(
                    onClick = {
                        if (selectedQuizIndex == (selectedLevel.quizes.size - 1)) {
                            navController.navigate("congrats")
                            return@Button
                        } else {
                            selectedQuizIndex++
                        }
                    }
                ) {
                    Text(
                        text = if(state.selectedLevelIndex == (quizes.size - 1)) "Finish" else "Next Quiz",
                    )
                }
            }
        }
    }
}

@Composable
fun QuizCard(quiz: Quiz) {
    var selectedRadioButtonIndex by remember {
        mutableIntStateOf(0)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = quiz.question
        )
        Spacer(modifier = Modifier.height(16.dp))

        Column {
            quiz.answers.forEachIndexed { index, answer ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = index == selectedRadioButtonIndex,
                        onClick = {
                            selectedRadioButtonIndex = index
                            println(answer)
                        },
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = answer
                    )
                }
            }
        }
    }
}

private fun pickAnswer(answer: String) {

}