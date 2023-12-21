package com.aymen.chatgpt.feature_chat.presentation.chat

sealed class ChatEvent {
    data object AskQuestion : ChatEvent()
    data class OnTextChange(val text: String) : ChatEvent()
}