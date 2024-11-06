package com.example.riseup.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.riseup.R
import com.example.riseup.model.quest.Quest
import com.example.riseup.ui.components.cards.QuestItemCard

@Composable
fun QuestList(quests: List<Quest>, onCardClick: (Quest) -> Unit) {
    if (quests.isEmpty()) {
        EmptyDataPlaceholder(stringResource(R.string.all_quests_completed))
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(quests) { quest ->
                QuestItemCard(
                    quest = quest,
                    onCardClick = { onCardClick(quest) }
                )
            }
        }
    }
}