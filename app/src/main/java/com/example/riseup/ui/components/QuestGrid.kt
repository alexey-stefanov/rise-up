package com.example.riseup.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.riseup.R
import com.example.riseup.model.quest.Quest
import com.example.riseup.ui.components.cards.QuestItemCard

@Composable
fun QuestGrid(quests: List<Quest>, onCardClick: (Quest) -> Unit) {
    if (quests.isEmpty()) {
        EmptyDataPlaceholder(stringResource(R.string.all_quests_completed))
    } else {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
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