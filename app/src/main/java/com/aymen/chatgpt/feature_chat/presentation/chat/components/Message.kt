package com.aymen.chatgpt.feature_chat.presentation.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.aymen.chatgpt.feature_chat.domain.model.Message
import com.aymen.chatgpt.feature_chat.domain.model.fromUser
import com.aymen.chatgpt.ui.theme.White

@Composable
fun Message(message: Message) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (message.fromUser) Arrangement.End else Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = if (message.fromUser) 16.dp else 0.dp,
                        topEnd = if (message.fromUser) 0.dp else 16.dp,
                        bottomStart = 16.dp,
                        bottomEnd = 16.dp
                    )
                )
                .background(if (message.fromUser) MaterialTheme.colorScheme.secondary else White)
                .padding(start = 10.dp, top = 5.dp, bottom = 10.dp, end = 10.dp)
        ) {
            Text(
                text = if (message.fromUser) "You" else "Chat GPT",
                style = MaterialTheme.typography.bodySmall,
                color = if (message.fromUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = message.content,
                style = MaterialTheme.typography.bodyLarge,
                color = if (message.fromUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}