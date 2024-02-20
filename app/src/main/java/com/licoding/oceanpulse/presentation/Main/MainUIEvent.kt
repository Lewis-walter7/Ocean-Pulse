package com.licoding.oceanpulse.presentation.Main

sealed interface MainUIEvent {
    data object OnLogOutButtonClicked: MainUIEvent

    data class OnSelectedLevelIndexChange(val index: Int): MainUIEvent
}