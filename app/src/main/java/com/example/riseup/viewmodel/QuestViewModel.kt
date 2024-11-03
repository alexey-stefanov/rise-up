package com.example.riseup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.riseup.model.character.Character
import com.example.riseup.model.quest.Quest
import com.example.riseup.model.quest.QuestDifficulty
import com.example.riseup.model.quest.QuestType
import com.example.riseup.repository.CharacterRepository
import com.example.riseup.repository.QuestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestViewModel @Inject constructor(
    private val questRepository: QuestRepository,
    private val characterRepository: CharacterRepository
) : ViewModel() {
    val quests: LiveData<List<Quest>> = questRepository.getAllQuests().asLiveData()
    val character: LiveData<Character?> = characterRepository.getCharacter().asLiveData()

    init {
        viewModelScope.launch {
            initializeCharacterIfNeeded()
            initializeDailyQuests()
        }
    }

    private suspend fun initializeCharacterIfNeeded() {
        characterRepository.initializeCharacterIfNeeded()
    }

    private suspend fun initializeDailyQuests() {
        questRepository.initializeDailyQuests()
    }

    fun getExperienceProgress(): Float {
        val currentXp = character.value?.currentXp ?: 0
        val xpForNextLevel = character.value?.xpForNextLevel ?: 100
        return currentXp.toFloat() / xpForNextLevel.toFloat()
    }

    fun addNewQuest(name: String, difficulty: QuestDifficulty) {
        val newQuest = Quest(
            id = 0,
            name = name,
            difficulty = difficulty,
            type = QuestType.REGULAR,
            isCompleted = false,
            isAccepted = false
        )
        viewModelScope.launch {
            questRepository.insertQuest(newQuest)
        }
    }

    fun acceptQuest(quest: Quest) {
        viewModelScope.launch {
            questRepository.acceptQuest(quest)
        }
    }

    fun declineQuest(quest: Quest) {
        viewModelScope.launch {
            questRepository.declineQuest(quest)
        }
    }

    fun completeQuest(quest: Quest) {
        viewModelScope.launch {
            character.value?.let {
                questRepository.completeQuest(quest)

                val xpGained = calculateXpForQuest(quest)

                var newLevel = it.level
                var newXp = it.currentXp + xpGained
                var xpForNextLevel = it.xpForNextLevel

                while (newXp >= xpForNextLevel) {
                    newXp -= xpForNextLevel
                    newLevel++
                    xpForNextLevel = calculateXpForNextLevel(newLevel)
                }

                updateCharacterLevelAndXp(newLevel, newXp, xpForNextLevel)
            }
        }
    }

    private fun calculateXpForQuest(quest: Quest): Int {
        return when (quest.difficulty) {
            QuestDifficulty.EASY -> 10
            QuestDifficulty.MEDIUM -> 20
            QuestDifficulty.HARD -> 30
        }
    }

    private fun calculateXpForNextLevel(level: Int): Int {
        return 100 + (level - 1) * 50
    }

    private suspend fun updateCharacterLevelAndXp(newLevel: Int, newXp: Int, xpForNextLevel: Int) {
        val updatedCharacter = Character(
            level = newLevel,
            currentXp = newXp,
            xpForNextLevel = xpForNextLevel
        )
        characterRepository.insertOrUpdateCharacter(updatedCharacter)
    }
}