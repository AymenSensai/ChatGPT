package com.aymen.chatgpt.feature_chat.data.repository

import com.aymen.chatgpt.core.Resource
import com.aymen.chatgpt.feature_chat.data.local.AnswerDao
import com.aymen.chatgpt.feature_chat.data.local.entity.AnswerEntity
import com.aymen.chatgpt.feature_chat.data.remote.ChatGptApi
import com.aymen.chatgpt.feature_chat.domain.model.Answer
import com.aymen.chatgpt.feature_chat.domain.model.Message
import com.aymen.chatgpt.feature_chat.domain.model.Question
import com.aymen.chatgpt.feature_chat.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ChatRepositoryImpl(private val api: ChatGptApi, private val dao: AnswerDao) : ChatRepository {

    override suspend fun askQuestions(
        previousQuestions: List<Message>,
        question: String
    ): Resource<Answer> {
        try {
            api.sendMessage(
                question = Question(
                    messages = previousQuestions + Message(
                        role = "user",
                        content = question
                    )
                )
            ).also {
                return if (it.isSuccessful) {
                    Resource.Success(it.body()!!)
                } else {
                    Resource.Error(it.errorBody()?.string().toString())
                }
            }
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
    }

    override suspend fun getMessages(): Flow<List<Message>> {
        return dao.getAnswers().map { answers ->
            answers.map {
                Message(it.role, it.content)
            }
        }
    }

    override suspend fun addAnswer(answer: Message) {
        dao.addAnswer(AnswerEntity(role = answer.role, content = answer.content))
    }

}