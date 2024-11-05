package com.example.riseup.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.riseup.R
import com.example.riseup.model.quest.Quest
import com.example.riseup.model.quest.QuestDifficulty
import com.example.riseup.model.quest.QuestState
import com.example.riseup.model.quest.QuestType
import com.example.riseup.ui.theme.Blue
import com.example.riseup.ui.theme.Gray
import com.example.riseup.ui.theme.Green
import com.example.riseup.ui.theme.Purple

@Composable
fun QuestItemCard(
    quest: Quest,
    onCardClick: () -> Unit
) {

    val questTypeIndicator = when (quest.difficulty) {
        QuestDifficulty.EASY -> Gray
        QuestDifficulty.MEDIUM -> Green
        QuestDifficulty.HARD -> Blue
        QuestDifficulty.EPIC -> Purple
    }

    val questIcon = when {
        quest.state == QuestState.New ->
            painterResource(R.drawable.exclamation_mark)

        quest.state == QuestState.Accepted && quest.type == QuestType.DAILY ->
            painterResource(R.drawable.question_mark_dark_green)

        quest.state == QuestState.Accepted && quest.type == QuestType.REGULAR ->
            painterResource(R.drawable.question_mark_gold)

        else -> painterResource(R.drawable.thumb_up)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onCardClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Box(
            modifier = Modifier
                .padding(2.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Image(
                    painter = questIcon,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = quest.name,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF4B3621)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(questTypeIndicator, shape = CircleShape)
                    )
                }
            }
        }
    }
}