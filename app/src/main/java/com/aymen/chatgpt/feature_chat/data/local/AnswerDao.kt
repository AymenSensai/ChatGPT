package com.aymen.chatgpt.feature_chat.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aymen.chatgpt.feature_chat.data.local.entity.AnswerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnswerDao {

    @Insert
    fun addAnswer(answerEntity: AnswerEntity)

    @Query("SELECT * FROM 'answers'")
    fun getAnswers(): Flow<List<AnswerEntity>>

}