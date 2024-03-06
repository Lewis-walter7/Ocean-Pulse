package com.licoding.oceanpulse.domain.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.licoding.oceanpulse.domain.models.User

@Database(
    entities = [User::class],
    version = 2,
    exportSchema = false
)
abstract class OceanPulseDb: RoomDatabase(){
    abstract val userDao: UserDao
}

//val MIGRATION_1_2: Migration = object : Migration(1, 2) {
//    override fun migrate(db: SupportSQLiteDatabase) {
//        db.execSQL("DROP TABLE IF EXISTS user")
//    }
//}

val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS users (userId TEXT PRIMARY KEY NOT NULL, email TEXT NOT NULL, bio TEXT, profileImage TEXT, createdAt BIGINT NOT NULL, name TEXT NOT NULL)")

        db.execSQL("INSERT INTO users (userId, email, bio, profileImage, createdAt, name) SELECT userId, email, bio, profileImage, createdAt, name FROM user")

        db.execSQL("DROP TABLE IF EXISTS user")

        db.execSQL("ALTER TABLE users RENAME TO user")
    }
}