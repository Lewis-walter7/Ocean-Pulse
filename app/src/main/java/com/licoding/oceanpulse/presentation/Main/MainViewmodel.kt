package com.licoding.oceanpulse.presentation.Main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.licoding.oceanpulse.domain.models.User
import com.licoding.oceanpulse.data.remote.repository.ApiImpl.getBlogs
import com.licoding.oceanpulse.data.remote.userRepo.GetUserArticles
import com.licoding.oceanpulse.domain.response.BlogResponse
import com.licoding.oceanpulse.domain.room.DatabaseSingleton
import com.licoding.oceanpulse.domain.services.GetArticles
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
    val userArticles = GetUserArticles.articles
    private val getArticles = GetArticles()
    val articles = getArticles.articles
    init {
        viewModelScope.launch {
            delay(1000)
            _state.update {
                it.copy(
                    user = appDatabase.userDao.getUser()
                )
            }
            posts = getBlogs()
            getArticles.fetchArticles()
        }
        GetUserArticles.getArticles()
        println(articles)
    }

    private val _state = MutableStateFlow(MainUIState())
    val state = _state.asStateFlow()

    fun onEvent(event: MainUIEvent) {
        when(event){
            MainUIEvent.OnLogOutButtonClicked -> {
                deleteUser()
            }

            is MainUIEvent.OnSelectedLevelIndexChange -> {
                _state.update {
                    it.copy(
                        selectedLevelIndex = event.index
                    )
                }
            }

            is MainUIEvent.UpdateFact -> {
                _state.update {
                    it.copy(
                        fact = event.fact
                    )
                }

                if (state.value.fact != null) {
                    println(state.value.fact)
                }
            }
        }
    }
    fun deleteUser(){
        viewModelScope.launch {
            appDatabase.userDao.deleteUser()
        }
    }
}