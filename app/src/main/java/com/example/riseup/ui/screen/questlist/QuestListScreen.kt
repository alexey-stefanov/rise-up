package com.example.riseup.ui.screen.questlist

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.riseup.R
import com.example.riseup.model.quest.Quest
import com.example.riseup.ui.components.dialogs.AddQuestDialog
import com.example.riseup.ui.components.ExperienceBar
import com.example.riseup.ui.components.dialogs.QuestDetailDialog
import com.example.riseup.ui.components.QuestGrid
import com.example.riseup.ui.components.QuestList
import com.example.riseup.ui.components.icons.Grid
import com.example.riseup.ui.screen.achievementscreen.AchievementsScreen
import com.example.riseup.ui.screen.character.CharacterScreen
import com.example.riseup.ui.screen.completedquestlist.QuestHistoryScreen
import com.example.riseup.viewmodel.QuestViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestListScreen(viewModel: QuestViewModel = hiltViewModel()) {
    val quests by viewModel.quests.observeAsState(emptyList())
    val character by viewModel.character.observeAsState()
    val achievements by viewModel.achievements.observeAsState()
    var selectedQuest by remember { mutableStateOf<Quest?>(null) }
    var showAddDialog by remember { mutableStateOf(false) }
    var showCharacterScreen by remember { mutableStateOf(false) }
    var showQuestHistoryScreen by remember { mutableStateOf(false) }
    var showAchievementsScreen by remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    var isGridView by remember { mutableStateOf(false) }

    val context = LocalContext.current

    // TODO Notifications and toasts
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 32.dp),
                drawerContainerColor = MaterialTheme.colorScheme.surface,
                drawerContentColor = MaterialTheme.colorScheme.onSurface
            ) {
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
                    // TODO move to another file MenuItem
                    HorizontalDivider()
                    NavigationDrawerItem(
                        label = { Text(stringResource(R.string.quest_history)) },
                        selected = false,
                        onClick = {
                            coroutineScope.launch {
                                drawerState.close()
                                showQuestHistoryScreen = true
                            }

                        },
                        modifier = Modifier.padding(16.dp)
                    )
                    HorizontalDivider()
                    NavigationDrawerItem(
                        label = { Text(stringResource(R.string.achievements)) },
                        selected = false,
                        onClick = {
                            coroutineScope.launch {
                                drawerState.close()
                                showAchievementsScreen = true
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
                    onBack = { showCharacterScreen = false }
                )
            } else if (showQuestHistoryScreen) {
                QuestHistoryScreen(
                    onBack = { showQuestHistoryScreen = false }
                )
            } else if (showAchievementsScreen) {
                AchievementsScreen(
                    onBack = { showAchievementsScreen = false }
                )
            } else {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(stringResource(R.string.quests)) },
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
                            character?.let {
                                ExperienceBar(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(28.dp)
                                        .padding(horizontal = 16.dp, vertical = 8.dp),
                                    progress = viewModel.getExperienceProgress()
                                )
                            }
                            // TODO fix infinite load
                            achievements?.let {

                            } ?: run {
                                viewModel.initializeAchievements()
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.End
                            ) {
                                IconButton(
                                    onClick = { isGridView = !isGridView }
                                ) {
                                    Icon(
                                        imageVector = if (!isGridView) Icons.AutoMirrored.Filled.List else Grid,
                                        contentDescription = if (isGridView) stringResource(R.string.show_as_list) else stringResource(
                                            R.string.show_as_grid
                                        )
                                    )
                                }
                            }
                            if (isGridView) {
                                QuestGrid(
                                    quests = quests,
                                    onCardClick = { quest -> selectedQuest = quest }
                                )
                            } else {
                                QuestList(
                                    quests = quests,
                                    onCardClick = { quest -> selectedQuest = quest }
                                )
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
                                        context.getString(
                                            R.string.quest_completed,
                                            quest.difficulty.xp
                                        ),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    selectedQuest = null
                                }
                            )
                        }

                        FloatingActionButton(
                            onClick = { showAddDialog = true },
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
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
                                onAddQuest = { questName, questDescription, questDifficulty ->
                                    viewModel.addNewQuest(
                                        questName,
                                        questDescription,
                                        questDifficulty
                                    )
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