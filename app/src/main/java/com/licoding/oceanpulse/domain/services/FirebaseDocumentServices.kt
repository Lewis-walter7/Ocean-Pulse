package com.licoding.oceanpulse.domain.services

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.licoding.oceanpulse.domain.models.Article
import com.licoding.oceanpulse.presentation.upload.getBooleanValue
import com.licoding.oceanpulse.presentation.upload.reset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirebaseDocumentServices {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val scope = CoroutineScope(Dispatchers.Main)
    private val db = Firebase.firestore
    fun createPostDocument(article: Article) {
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
                        getBooleanValue {
                            reset(true)
                        }
                    }
                }
                .addOnFailureListener{
                    scope.launch(Dispatchers.Main) {
                        getBooleanValue {
                            reset(false)
                        }
                    }
                }
        }
    }
}