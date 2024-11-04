package com.example.riseup.ui.screen.completedquestlist

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.riseup.R
import com.example.riseup.viewmodel.CompletedQuestViewModel
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestHistoryScreen(
    onBack: () -> Unit,
    viewModel: CompletedQuestViewModel = hiltViewModel()
) {
    val completedQuests by viewModel.completedQuests.observeAsState(emptyList())

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
        content = { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(completedQuests) { quest ->
                    Text(
                        "Квест: ${quest.name} - Сложность: ${quest.difficulty} - Дата завершения: ${
                            Date(
                                quest.completionDate
                            )
                        }"
                    )
                }
            }
        }
    )
}