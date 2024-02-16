package com.licoding.oceanpulse.domain.utils

import kotlinx.coroutines.flow.Flow

interface NetworkObserver {
    fun observerNetworkStatus(): Flow<NetworkStatus>

    enum class NetworkStatus {
        Available, UnAvailable, Lost, Loosing
    }
}