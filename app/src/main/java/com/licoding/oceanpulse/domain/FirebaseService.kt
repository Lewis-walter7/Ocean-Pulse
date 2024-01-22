package com.licoding.oceanpulse.domain

import android.app.Application
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.google.firebase.auth.FirebaseAuth
import com.licoding.oceanpulse.domain.requests.AuthRequest
import kotlinx.coroutines.*

class FirebaseService(
    private val application: Application,
    val navigate: () -> Unit,
) {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val scope = CoroutineScope(Dispatchers.Main)
    fun createUser(authRequest: AuthRequest){
        firebaseAuth.createUserWithEmailAndPassword(authRequest.email, authRequest.password).addOnCompleteListener{ task ->
            if (task.isSuccessful) {
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

    fun signIn(authRequest: AuthRequest){
        firebaseAuth.signInWithEmailAndPassword(authRequest.email, authRequest.password).addOnCompleteListener{ task ->
            if (task.isSuccessful) {
                scope.launch {
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