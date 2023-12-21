package com.aymen.chatgpt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.aymen.chatgpt.feature_chat.presentation.chat.ChatScreen
import com.aymen.chatgpt.feature_chat.presentation.chat.ChatViewModel
import com.aymen.chatgpt.ui.theme.ChatGPTTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatGPTTheme {
                val viewModel: ChatViewModel = hiltViewModel()
                val state = viewModel.state
                ChatScreen(state = state, onEvent = { viewModel.onEvent(it) })
            }
        }
    }
}