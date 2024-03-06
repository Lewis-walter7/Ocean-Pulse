package com.licoding.oceanpulse.data.remote.userRepo

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.licoding.oceanpulse.domain.models.User
import kotlinx.coroutines.tasks.await


val firestore = FirebaseFirestore.getInstance()
suspend fun getUserById(id: String) : User{
    val userDocument = firestore.collection("users").document(id)

    val document = userDocument.get().await()
    val user = snapshotToUser(document)

    println(user)
    return user
}


fun snapshotToUser(snapshot: DocumentSnapshot): User {
    return User(
        userId = snapshot.id,
        profileImage = snapshot.data?.get("profileImage")?.toString(),
        bio = snapshot.data?.get("bio")?.toString(),
        name = snapshot.data!!["name"].toString(),
        createdAt = snapshot.data?.get("createdAt") as Long,
        email = snapshot.data!!["email"].toString(),
    )
}
