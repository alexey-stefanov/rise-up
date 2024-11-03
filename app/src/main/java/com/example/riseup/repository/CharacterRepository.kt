package com.example.riseup.repository

import com.example.riseup.model.character.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacter(): Flow<Character?>
    suspend fun initializeCharacterIfNeeded()
    suspend fun insertOrUpdateCharacter(character: Character)
}