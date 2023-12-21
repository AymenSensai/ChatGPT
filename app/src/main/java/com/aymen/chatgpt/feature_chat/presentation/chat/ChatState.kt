package com.aymen.chatgpt.feature_chat.presentation.chat

import com.aymen.chatgpt.feature_chat.domain.model.Message

data class ChatState(
    val messages: List<Message> = emptyList(),
    val text: String = "",
    val isLoading: Boolean = false
)
