package com.aymen.chatgpt.feature_chat.presentation.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aymen.chatgpt.R
import com.aymen.chatgpt.feature_chat.presentation.chat.components.Message
import com.aymen.chatgpt.ui.theme.Black
import com.aymen.chatgpt.ui.theme.Grey
import com.aymen.chatgpt.ui.theme.LightBlue

@Composable
fun ChatScreen(
    state: ChatState,
    onEvent: (ChatEvent) -> Unit,
) {

    val focusManager = LocalFocusManager.current

    Scaffold(topBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(MaterialTheme.colorScheme.primary)
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.chat_gpt_logo),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "Chat Gpt",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(text = "online", color = Grey, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            if (state.isLoading) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                reverseLayout = true
            ) {
                items(state.messages.reversed()) { message ->
                    Message(message)
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp)
            ) {
                TextField(
                    value = state.text,
                    onValueChange = { text ->
                        onEvent(ChatEvent.OnTextChange(text))
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            focusManager.clearFocus()
                            onEvent(ChatEvent.AskQuestion)
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.send),
                                contentDescription = "Send text"
                            )
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = LightBlue,
                        focusedContainerColor = LightBlue,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = Black,
                        unfocusedTextColor = Black,
                        cursorColor = Black,
                        focusedTrailingIconColor = MaterialTheme.colorScheme.secondary,
                        unfocusedTrailingIconColor = MaterialTheme.colorScheme.secondary
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    placeholder = {
                        Text(text = "Ask question...", style = MaterialTheme.typography.bodyLarge)
                    }
                )
            }
        }
    }
}