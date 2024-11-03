package com.example.riseup.repository.implementation

import com.example.riseup.data.local.dao.CharacterDao
import com.example.riseup.data.local.entities.CharacterEntity
import com.example.riseup.model.character.Character
import com.example.riseup.repository.CharacterRepository
import com.example.riseup.util.CharacterMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepositoryImpl @Inject constructor(
    private val characterDao: CharacterDao
) : CharacterRepository {
    override fun getCharacter(): Flow<Character?> {
        return characterDao.getCharacter().map {
            it?.let { CharacterMapper.fromEntity(it) }
        }
    }

    override suspend fun initializeCharacterIfNeeded() {
        val characterExists = characterDao.getCharacter().firstOrNull() != null
        if (!characterExists) {
            val newCharacter = CharacterEntity(id = 1, level = 1, currentXp = 0, xpForNextLevel = 100)
            characterDao.insertOrUpdateCharacter(newCharacter)
        }
    }

    override suspend fun insertOrUpdateCharacter(character: Character) {
        characterDao.insertOrUpdateCharacter(CharacterMapper.toEntity(character))
    }
}