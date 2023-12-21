package com.aymen.chatgpt.feature_chat.domain.model

import com.google.gson.annotations.SerializedName

data class Question(
    val model: String = "gpt-3.5-turbo",
    val messages: List<Message>
)

data class Message(
    @SerializedName("role")
    val role: String,
    @SerializedName("content")
    val content: String
)

val Message.fromUser: Boolean
    get() {
        return role == "user"
    }