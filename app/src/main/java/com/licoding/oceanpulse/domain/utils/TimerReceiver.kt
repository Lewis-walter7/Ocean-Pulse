package com.licoding.oceanpulse.domain.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.licoding.oceanpulse.data.constants.facts
import com.licoding.oceanpulse.data.constants.getFact
import com.licoding.oceanpulse.presentation.Main.MainUIEvent

class TimerReceiver(
    private val onEvent: (MainUIEvent) -> Unit
): BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val fact = getFact(facts)
        onEvent(MainUIEvent.UpdateFact(fact))
    }
}