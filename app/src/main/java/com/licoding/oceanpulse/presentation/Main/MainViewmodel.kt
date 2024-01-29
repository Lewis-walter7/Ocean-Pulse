package com.licoding.oceanpulse.presentation.Main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.licoding.oceanpulse.data.models.User
import com.licoding.oceanpulse.data.remote.repository.getBlogs
import com.licoding.oceanpulse.domain.response.BlogResponse
import com.licoding.oceanpulse.domain.room.DatabaseSingleton
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewmodel(
    application: Application
): ViewModel() {
    private val appDatabase = DatabaseSingleton.getDatabase(application)
    var user: User? = null
    var posts: List<BlogResponse> = emptyList()
    init {
        viewModelScope.launch {
            delay(1000)
            _state.update {
                it.copy(
                    user = appDatabase.userDao.getUser()
                )
            }
            posts = getBlogs()
        }
    }

    private val _state = MutableStateFlow(MainUIState())
    val state = _state.asStateFlow()

    fun onEvent(event: MainUIEvent) {
        when(event){
            MainUIEvent.OnLogOutButtonClicked -> {
                deleteUser()
            }
        }
    }
    fun deleteUser(){
        viewModelScope.launch {
            appDatabase.userDao.deleteUser()
        }
    }

}