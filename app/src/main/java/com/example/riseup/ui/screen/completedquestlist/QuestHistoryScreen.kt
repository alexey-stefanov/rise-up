package com.example.riseup.ui.screen.completedquestlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.riseup.R
import com.example.riseup.model.quest.Quest
import com.example.riseup.ui.components.CompletedQuestsDateHeader
import com.example.riseup.ui.components.EmptyDataPlaceholder
import com.example.riseup.ui.components.dialogs.QuestDetailDialog
import com.example.riseup.ui.components.cards.QuestItemCard
import com.example.riseup.viewmodel.CompletedQuestViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestHistoryScreen(
    onBack: () -> Unit,
    viewModel: CompletedQuestViewModel = hiltViewModel()
) {
    val groupedCompletedQuests by viewModel.groupedCompletedQuests.observeAsState(emptyMap())
    var selectedQuest by remember { mutableStateOf<Quest?>(null) }

    // TODO Move to a RiseUpTopAppBar
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.quest_history)) },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (groupedCompletedQuests.values.isEmpty()) {
                    EmptyDataPlaceholder(stringResource(R.string.no_completed_quests))
                } else {
                    LazyColumn {
                        groupedCompletedQuests.forEach { (date, quests) ->
                            item {
                                CompletedQuestsDateHeader(date)
                            }
                            items(quests) { quest ->
                                QuestItemCard(
                                    quest = quest,
                                    onCardClick = { selectedQuest = quest }
                                )
                            }
                        }
                    }
                }

                selectedQuest?.let { quest ->
                    QuestDetailDialog(
                        quest = quest,
                        onDismiss = { selectedQuest = null },
                        onAcceptQuest = {},
                        onDeclineQuest = {},
                        onCompleteQuest = {}
                    )
                }
            }
        }
    )
}