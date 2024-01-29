package com.licoding.oceanpulse.presentation.Main.components

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

@Composable
fun InfoTab(
    context: Context
) {

    lateinit var tts: TextToSpeech
    var currentSpeechPosition: Int = 0
    var isSpeaking by remember { mutableStateOf(false) }
    val text = "The ocean drives global systems that make the Earth habitable for humankind. Our rainwater, drinking water, weather, climate, coastlines, much of our food, and even the oxygen in the air we breathe, are all ultimately provided and regulated by the sea.\n" +
            "\n" +
            "Careful management of this essential global resource is a key feature of a sustainable future. However, at the current time, there is a continuous deterioration of coastal waters owing to pollution, and ocean acidification is having an adversarial effect on the functioning of ecosystems and biodiversity. This is also negatively impacting small scale fisheries.\n" +
            "\n" +
            "Saving our ocean must remain a priority. Marine biodiversity is critical to the health of people and our planet. Marine protected areas need to be effectively managed and well-resourced and regulations need to be put in place to reduce overfishing, marine pollution and ocean acidification."
    Column {
        Row(
            modifier = Modifier
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Life Below Water",
                fontSize = 20.sp,
                modifier = Modifier
                    .weight(1f)
            )
            IconButton(
                onClick = {
                    isSpeaking = !isSpeaking
                    if(isSpeaking) {
                        tts = TextToSpeech(context) {
                            if (it == TextToSpeech.SUCCESS) {
                                tts.language = Locale.UK
                                tts.setSpeechRate(1.0f)
                                tts.speak(text.trim(), TextToSpeech.QUEUE_ADD, null)
                            }
                        }
                    } else {
                        tts.stop()
                    }

                    tts.setOnUtteranceProgressListener(object : UtteranceProgressListener(){
                        override fun onStart(utteranceId: String?) {
                            TODO("Not yet implemented")
                        }

                        override fun onDone(utteranceId: String?) {
                            isSpeaking = false
                        }

                        override fun onError(utteranceId: String?) {
                            TODO("Not yet implemented")
                        }
                    })
                }
            ) {
                Icon(
                    imageVector = if (isSpeaking) Icons.Default.Pause else Icons.Default.PlayArrow,
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = text
        )
    }
}