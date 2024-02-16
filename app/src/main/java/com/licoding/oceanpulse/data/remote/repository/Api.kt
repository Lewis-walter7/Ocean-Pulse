package com.licoding.oceanpulse.data.remote.repository

import com.licoding.oceanpulse.data.models.Article
import com.licoding.oceanpulse.domain.response.BlogResponse


interface Api {
    suspend fun getBlogs(): List<BlogResponse>

    suspend fun uploadArticle(article: Article)

}