package com.example.riseup.ui.screens.QuestListScreen

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.riseup.R
import com.example.riseup.data.model.Quest
import com.example.riseup.data.model.QuestDifficulty
import com.example.riseup.data.model.QuestType

@Composable
fun QuestItemCard(
    quest: Quest,
    onCardClick: () -> Unit
) {

    val gradientBorder = when (quest.type) {
        QuestType.DAILY -> Brush.linearGradient(
            colors = listOf(Color(0xFF42A5F5), Color(0xFF1E88E5))
        )

        QuestType.REGULAR -> Brush.linearGradient(
            colors = listOf(Color(0xFFFFD700), Color(0xFFFFA500))
        )
    }

    val difficultyColor = when (quest.difficulty) {
        QuestDifficulty.EASY -> Color(0xFF66BB6A)
        QuestDifficulty.MEDIUM -> Color(0xFFFFEB3B)
        QuestDifficulty.HARD -> Color(0xFFEF5350)
    }

    val parchmentTexture: Painter = painterResource(R.drawable.parchment_texture)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(gradientBorder)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onCardClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .background(brush = gradientBorder)
                .padding(2.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            Image(
                painter = parchmentTexture,
                contentDescription = null,
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = when (quest.type) {
                        QuestType.DAILY -> Icons.Default.Star
                        QuestType.REGULAR -> Icons.Default.Face
                    },
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = Color.Gray
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
                            .background(difficultyColor, shape = CircleShape)
                    )
                }
            }
        }
    }
}