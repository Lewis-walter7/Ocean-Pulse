package com.licoding.oceanpulse.data.remote.repository

import com.licoding.oceanpulse.domain.models.Article
import com.licoding.oceanpulse.domain.models.HttpRoutes
import com.licoding.oceanpulse.data.remote.client
import com.licoding.oceanpulse.domain.response.BlogResponse
import io.ktor.client.call.*
import io.ktor.client.request.*

object ApiImpl: Api {
    override suspend fun uploadArticle(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun getBlogs(): List<BlogResponse> {
        val response = client.get {
            url(HttpRoutes.BLOGS)
        }
        return response.body()
    }
}