package com.aymen.chatgpt.feature_chat.domain.repository

import com.aymen.chatgpt.core.Resource
import com.aymen.chatgpt.feature_chat.domain.model.Answer
import com.aymen.chatgpt.feature_chat.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    suspend fun askQuestions(previousQuestions: List<Message>, question: String): Resource<Answer>
    suspend fun getMessages(): Flow<List<Message>>
    suspend fun addAnswer(answer: Message)

}