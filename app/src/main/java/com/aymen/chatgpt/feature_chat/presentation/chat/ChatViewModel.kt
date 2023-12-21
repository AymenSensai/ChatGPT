package com.aymen.chatgpt.feature_chat.presentation.chat

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aymen.chatgpt.core.Resource
import com.aymen.chatgpt.feature_chat.domain.model.Message
import com.aymen.chatgpt.feature_chat.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {

    var state by mutableStateOf(ChatState())
        private set

    init {
        viewModelScope.launch {
            chatRepository.getMessages().collect { data ->
                state = state.copy(messages = data)
            }
        }
    }

    fun onEvent(event: ChatEvent) {
        when (event) {
            is ChatEvent.AskQuestion -> askQuestion()
            is ChatEvent.OnTextChange -> onTextChange(event.text)
        }
    }

    private fun askQuestion() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val question = state.text
                chatRepository.addAnswer(Message(role = "user", content = question))
                state = state.copy(isLoading = true, text = "")
                chatRepository.askQuestions(
                    previousQuestions = state.messages,
                    question = question
                )
                    .also {
                        state = state.copy(isLoading = false)
                        when (val result = it) {
                            is Resource.Success -> {
                                withContext(Dispatchers.IO) {
                                    chatRepository.addAnswer(
                                        Message(
                                            role = "assistant",
                                            content = result.data!!.choices.first().message.content
                                        )
                                    )
                                }
                            }

                            is Resource.Error -> {
                                Log.d("Error: ", result.message.toString())
                            }

                            else -> {}
                        }
                    }
            }
        }
    }

    private fun onTextChange(text: String) {
        state = state.copy(text = text)
    }

}