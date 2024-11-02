package com.example.riseup.ui.screens.QuestListScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.riseup.R
import com.example.riseup.data.model.Quest

@Composable
fun QuestListScreen(viewModel: QuestViewModel = viewModel()) {
    val quests by remember { derivedStateOf { viewModel.quests } }
    var selectedQuest by remember { mutableStateOf<Quest?>(null)}
    var showAddDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            QuestListHeader()
            LazyColumn {
                items(quests) { quest ->
                    QuestItemCard(
                        quest = quest,
                        onCardClick = { selectedQuest = quest }
                    )
                }
            }
        }

        selectedQuest?.let { quest ->
            QuestDetailDialog(
                quest = quest,
                onDismiss = { selectedQuest = null },
                onAcceptQuest = {
                    viewModel.acceptQuest(quest)
                    selectedQuest = null
                },
                onDeclineQuest = {
                    viewModel.declineQuest(quest)
                    selectedQuest = null
                },
                onCompleteQuest = {
                    viewModel.completeQuest(quest)
                    Toast.makeText(
                        context,
                        context.getString(R.string.quest_completed, quest.xp),
                        Toast.LENGTH_SHORT
                    ).show()
                    selectedQuest = null
                }
            )
        }

        FloatingActionButton(
            onClick = { showAddDialog = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add_quest))
        }

        if(showAddDialog) {
            AddQuestDialog(
                onAddQuest = { questName, difficulty ->
                    viewModel.addQuest(questName, difficulty)
                    showAddDialog = false
                },
                onDismiss = { showAddDialog = false }
            )
        }
    }
}