package com.aymen.chatgpt.feature_chat.data.remote

import com.aymen.chatgpt.core.Constants.API_KEY
import com.aymen.chatgpt.feature_chat.domain.model.Answer
import com.aymen.chatgpt.feature_chat.domain.model.Question
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChatGptApi {

    @POST("completions")
    @Headers("Authorization: Bearer $API_KEY", "Content-Type: application/json")
    suspend fun sendMessage(@Body question: Question): Response<Answer>

}