package com.licoding.oceanpulse.domain.services

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.licoding.oceanpulse.data.models.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirebaseDocumentServices {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val scope = CoroutineScope(Dispatchers.Main)
    private val db = Firebase.firestore
    suspend fun createPostDocument(article: Article) {
        article.let {
            val post = hashMapOf(
                "createdAt" to article.createdAt,
                "id" to article.id,
                "body" to article.body,
                "title" to article.title,
                "image" to article.image,
                "userId" to (firebaseAuth.currentUser?.uid ?: "")
            )
            db.collection("posts").document(it.id!!)
                .set(post)
                .addOnSuccessListener {
                    scope.launch(Dispatchers.Main) {
                        println("Successfully created the document")
                    }
                }
                .addOnFailureListener{
                    scope.launch(Dispatchers.Main) {
                        println("Failed to add the document")
                    }
                }
        }
    }
}