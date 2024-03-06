package com.licoding.oceanpulse.domain.services

import android.annotation.SuppressLint
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore
import com.licoding.oceanpulse.domain.models.Article

@SuppressLint("StaticFieldLeak")
object GetArticles {
    private val db = Firebase.firestore
    private val articlesCollection = db.collection("posts")
    private val articles = mutableListOf<Article>()
    fun fetchArticles(): List<Article> {
        articlesCollection.get().addOnSuccessListener { querySnapshot ->
            querySnapshot.documents.forEach{ documentSnapshot ->
                val article = documentSnapshot.toArticle()
                articles.add(article)
            }
        }

        return articles
    }
}

private fun DocumentSnapshot.toArticle(): Article {
    val id = getString("id")
    val userId = getString("userId")
    val title = getString("title")
    val body = getString("body")
    val createdAt = getLong("createdAt")
    val image = getString("image")
    return Article(
        id = id,
        userId = userId,
        createdAt = createdAt!!.toLong(),
        image = image,
        body = body ?: "",
        title = title ?: ""
    )
}
