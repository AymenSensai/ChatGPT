package com.aymen.chatgpt.feature_chat.data.local.entity

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aymen.chatgpt.feature_chat.data.local.AnswerDao

@Database(entities = [AnswerEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun answerDao(): AnswerDao

    companion object {
        const val DATABASE_NAME = "app_db"
    }

}