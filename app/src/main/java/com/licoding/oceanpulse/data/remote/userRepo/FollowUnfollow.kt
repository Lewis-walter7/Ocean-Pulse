package com.licoding.oceanpulse.data.remote.userRepo

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.licoding.oceanpulse.domain.models.User

object FollowUnfollow {
    private val currentUser = Firebase.auth.currentUser
    private val firebaseDb = FirebaseDatabase.getInstance().reference
    fun followUser(user: User){
        currentUser?.uid.let { uid ->
            firebaseDb
                .child("Follow").child(uid.toString())
                .child("Following").child(user.userId)
                .setValue(true)
                .addOnSuccessListener {
                    currentUser?.uid.let {uid ->
                        firebaseDb
                            .child("Follow").child(user.userId)
                            .child("Followers").child(uid.toString())
                            .setValue(true)
                    }
                }
        }
    }

    fun unfollowUser(user: User) {
        currentUser?.uid.let { uid ->
            firebaseDb
                .child("Follow").child(uid.toString())
                .child("Following").child(user.userId)
                .removeValue()
                .addOnSuccessListener {
                    currentUser?.uid.let {uid ->
                        firebaseDb
                            .child("Follow").child(user.userId)
                            .child("Followers").child(uid.toString())
                            .setValue(true)
                    }
                }
        }
    }

    fun checkFollowingStatus(uid: String): Boolean {
        var isFollowing = false
        val followingRef = currentUser?.uid.let {  it1 ->
            firebaseDb
                .child("Follow").child(it1.toString())
                .child("Following")
        }

        followingRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                isFollowing = snapshot.child(uid).exists()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        return isFollowing
    }
}