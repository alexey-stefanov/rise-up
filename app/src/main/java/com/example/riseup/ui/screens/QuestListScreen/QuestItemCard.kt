package com.example.riseup.ui.screens.QuestListScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.riseup.R
import com.example.riseup.data.model.Quest
import com.example.riseup.data.model.QuestType

@Composable
fun QuestItemCard(
    quest: Quest,
    onCardClick: () -> Unit
) {

    val backgroundColor = when(quest.type) {
        QuestType.DAILY -> colorResource(R.color.light_blue)
        QuestType.REGULAR -> colorResource(R.color.light_yellow)
    }

    val icon = when(quest.type) {
        QuestType.DAILY -> Icons.Default.Star
        QuestType.REGULAR -> Icons.Default.Face
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onCardClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = stringResource(R.string.quest),
                modifier = Modifier.size(40.dp),
                tint = Color.Gray)

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = quest.name,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}