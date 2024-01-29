package com.licoding.oceanpulse.domain.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.licoding.oceanpulse.data.models.User

@Dao
interface UserDao {
    @Upsert
    suspend fun upsertUser(user: User)

    @Query("SELECT * FROM user")
    suspend fun getUser(): User

    @Query("DELETE FROM user")
    suspend fun deleteUser()
}