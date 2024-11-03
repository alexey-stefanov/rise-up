package com.example.riseup.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.riseup.model.quest.Quest
import com.example.riseup.R
import com.example.riseup.model.quest.QuestType

@Composable
fun QuestDetailDialog(
    quest: Quest,
    onDismiss: () -> Unit,
    onAcceptQuest: () -> Unit,
    onDeclineQuest: () -> Unit,
    onCompleteQuest: () -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = quest.name, style = MaterialTheme.typography.headlineMedium)

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = quest.description ?: stringResource(R.string.description_missing),
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "${quest.xp} ${stringResource(R.string.xp)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {

                    if(quest.type == QuestType.REGULAR && !quest.isAccepted && !quest.isCompleted) {
                        TextButton(onClick = { onAcceptQuest() }) {
                            Text(stringResource(R.string.accept))
                        }
                    }

                    if(quest.type == QuestType.REGULAR && quest.isAccepted && !quest.isCompleted) {
                        TextButton(onClick = { onDeclineQuest() }) {
                            Text(stringResource(R.string.decline))
                        }
                    }

                    if(!quest.isCompleted && quest.isAccepted || quest.type == QuestType.DAILY) {
                        TextButton(onClick = { onCompleteQuest() }) {
                            Text(stringResource(R.string.completed))
                        }
                    }
                }

                TextButton(onClick = { onDismiss() }) {
                    Text(stringResource(R.string.cancel))
                }
            }
        }
    }
}