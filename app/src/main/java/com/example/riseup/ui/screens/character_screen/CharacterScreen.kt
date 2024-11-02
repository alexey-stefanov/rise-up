package com.example.riseup.ui.screens.character_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.riseup.R
import com.example.riseup.data.model.character.Character

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterScreen(
    onBack: () -> Unit,
    character: Character
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.character)) },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                "${stringResource(R.string.current_level)} ${character.level}",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "${stringResource(R.string.current_xp)} ${character.currentXp} / ${character.xpForNextLevel}",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(stringResource(R.string.achievements), style = MaterialTheme.typography.headlineMedium)
            if (character.achievements.isNotEmpty()) {
                character.achievements.forEach { achievement ->
                    Text(text = achievement, style = MaterialTheme.typography.bodyMedium)
                }
            } else {
                Text(
                    stringResource(R.string.no_achievements),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
    }
}