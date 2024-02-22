package com.licoding.oceanpulse.data.remote.userRepo

import android.annotation.SuppressLint
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.firestore
import com.licoding.oceanpulse.data.models.Article

@SuppressLint("StaticFieldLeak")
object GetUserArticles {

    private val db = Firebase.firestore
    private val postCollection = db.collection("posts")
    private val currentUser = FirebaseAuth.getInstance().currentUser

    private val query = postCollection.whereEqualTo("userId", currentUser?.uid)

    val articles = mutableListOf<Article>()

    fun getArticles() =
        query.get()
            .addOnSuccessListener { documents ->
                for (doc in documents) {
                    val article = snapshotToArticle(doc)
                    articles.add(article)
                    println(articles)
                }
            }

    fun snapshotToArticle(snapshot: QueryDocumentSnapshot): Article {
        return Article(
            id = snapshot.id,
            image = snapshot.data["image"].toString(),
            title = snapshot.data["title"].toString(),
            body = snapshot.data["body"].toString(),
            createdAt = snapshot.data["createdAt"] as Long,
            userId = snapshot.data["userId"].toString(),
        )
    }
}