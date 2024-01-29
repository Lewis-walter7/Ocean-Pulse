package com.licoding.oceanpulse.domain.room

import android.content.Context
import androidx.room.Room

object DatabaseSingleton {
    private var INSTANCE: OceanPulseDb? = null

    fun getDatabase(context: Context): OceanPulseDb {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                OceanPulseDb::class.java,
                "oceanpulse.db"
            ).addMigrations(MIGRATION_1_2).build()
            INSTANCE = instance
            instance
        }
    }
}