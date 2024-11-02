package com.example.riseup.ui.screens.quest_list

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.riseup.R
import com.example.riseup.data.model.quest.Quest
import com.example.riseup.ui.screens.character_screen.CharacterScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestListScreen(viewModel: QuestViewModel = viewModel()) {
    val quests by remember { derivedStateOf { viewModel.quests } }
    var selectedQuest by remember { mutableStateOf<Quest?>(null) }
    var showAddDialog by remember { mutableStateOf(false) }
    var showCharacterScreen by remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column {
                    Text(
                        text = stringResource(R.string.menu),
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                    HorizontalDivider()
                    NavigationDrawerItem(
                        label = { Text(stringResource(R.string.character)) },
                        selected = false,
                        onClick = {
                            coroutineScope.launch {
                                drawerState.close()
                                showCharacterScreen = true
                            }

                        },
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        },
        content = {
            if (showCharacterScreen) {
                CharacterScreen(
                    onBack = { showCharacterScreen = false },
                    character = viewModel.character
                )
            } else {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("") },
                            navigationIcon = {
                                IconButton(onClick = {
                                    coroutineScope.launch { drawerState.open() }
                                }) {
                                    Icon(
                                        Icons.Default.Menu,
                                        contentDescription = stringResource(R.string.open_menu)
                                    )
                                }
                            }
                        )
                    }
                ) { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        Column {
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
                            Icon(
                                Icons.Default.Add,
                                contentDescription = stringResource(R.string.add_quest)
                            )
                        }

                        if (showAddDialog) {
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
            }
        }
    )
}