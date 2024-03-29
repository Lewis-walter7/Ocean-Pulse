package com.licoding.oceanpulse.domain.services

import android.app.Application
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore
import com.licoding.oceanpulse.domain.models.User
import com.licoding.oceanpulse.domain.requests.AuthRequest
import com.licoding.oceanpulse.domain.room.OceanPulseDb
import kotlinx.coroutines.*

class FirebaseSignUpService(
    private val application: Application,
    val navigate: () -> Unit,
) {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val scope = CoroutineScope(Dispatchers.Main)
    private val db = Firebase.firestore

    fun createUser(authRequest: AuthRequest, appDatabase: OceanPulseDb){
        if (authRequest.name == null) {
            return
        } else {
            firebaseAuth.createUserWithEmailAndPassword(authRequest.email, authRequest.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = task.result.user
                        createUserDocument(user = user, name = authRequest.name)
                        val docRef = db.collection("users").document(user?.uid ?: "")
                        docRef.get()
                            .addOnSuccessListener { document ->
                                if (user != null) {
                                    scope.launch(Dispatchers.IO) {
                                        appDatabase.userDao.upsertUser(document.toUser())
                                    }
                                }
                            }
                            .addOnFailureListener {
                                Log.d("Failed to upsert user", it.message.toString())
                            }


                        scope.launch {
                            navigate()
                            Toast.makeText(application, "Authentication Successful", LENGTH_SHORT).show()
                        }
                    } else {
                        scope.launch {
                            Toast.makeText(application, "Authentication Failed", LENGTH_SHORT).show()
                        }
                    }
                }
        }
    }

    fun signIn(authRequest: AuthRequest, appDatabase: OceanPulseDb){
        firebaseAuth.signInWithEmailAndPassword(authRequest.email, authRequest.password).addOnCompleteListener{ task ->
            if (task.isSuccessful) {
                val user = task.result.user
                val docRef = db.collection("users").document(user?.uid ?: "")
                docRef.get()
                    .addOnSuccessListener { document ->
                        if (user != null) {
                            scope.launch(Dispatchers.IO) {
                                appDatabase.userDao.upsertUser(document.toUser())
                            }
                        }
                    }
                    .addOnFailureListener {
                        Log.d("Failed to upsert user", it.message.toString())
                    }
                scope.launch {
                    navigate()
                    Toast.makeText(application, "Authentication Successful", LENGTH_SHORT).show()
                }
            } else {
                scope.launch {
                    Toast.makeText(application, "Authentication Failed", LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun createUserDocument(user: FirebaseUser?, bio: String? = null, name: String) {
        user?.let {
            val user1 = hashMapOf(
                "profileImage" to it.photoUrl,
                "userId" to it.uid,
                "name" to name,
                "bio" to bio,
                "email" to  it.email,
                "createdAt" to System.currentTimeMillis()
            )
            db.collection("users").document(it.uid)
                .set(user1)
                .addOnSuccessListener {
                    println("Successfully created the document")
                }
                .addOnFailureListener{
                    println("Failed to add the document")
                }
        }
    }

    private fun DocumentSnapshot.toUser(): User {
        val email = getString("email")
        val profileImage = getString("profileImage")
        val name = getString("name")
        val bio = getString("bio")
        val userId = getString("userId")
        val createdAt = getLong("createdAt")
        return User(
            email = email!!,
            name = name!!,
            createdAt = createdAt!!,
            profileImage = profileImage,
            bio = bio,
            userId = userId!!
        )
    }
}