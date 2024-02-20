package com.licoding.oceanpulse.presentation.Main

import com.licoding.oceanpulse.data.models.Fact

sealed interface MainUIEvent {
    data object OnLogOutButtonClicked: MainUIEvent

    data class OnSelectedLevelIndexChange(val index: Int): MainUIEvent

    data class UpdateFact(val fact: Fact): MainUIEvent
}