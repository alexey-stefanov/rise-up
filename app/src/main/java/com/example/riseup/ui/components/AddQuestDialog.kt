package com.example.riseup.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.riseup.R
import com.example.riseup.model.quest.QuestDifficulty

@Composable
fun AddQuestDialog(
    onAddQuest: (String, String, QuestDifficulty) -> Unit,
    onDismiss: () -> Unit
) {
    var questName by remember { mutableStateOf("") }
    var questDescription by remember { mutableStateOf("") }
    var selectedDifficulty by remember { mutableStateOf(QuestDifficulty.EASY) }

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
                Text(text = stringResource(R.string.add_quest), style = MaterialTheme.typography.titleMedium)

                OutlinedTextField(
                    value = questName,
                    onValueChange = { questName = it },
                    label = { Text(stringResource(R.string.quest_name)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = questDescription,
                    onValueChange = { questDescription = it },
                    label = { Text(stringResource(R.string.description)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = stringResource(R.string.difficulty), style = MaterialTheme.typography.bodyMedium)

                QuestDifficulty.entries.forEach { difficulty ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedDifficulty = difficulty }
                    ) {
                        RadioButton(
                            selected = (selectedDifficulty == difficulty),
                            onClick = { selectedDifficulty = difficulty },
                            colors = RadioButtonDefaults.colors(selectedColor = Color.Blue)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = difficulty.name)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { onDismiss() }) {
                        Text(stringResource(R.string.cancel))
                    }
                    TextButton(onClick = {
                        if(questName.isNotBlank()) {
                            onAddQuest(questName, questDescription, selectedDifficulty)
                        }
                    }) {
                        Text(stringResource(R.string.add))
                    }
                }
            }
        }
    }
}