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
import com.example.riseup.repository.CompletedQuestRepository
import com.example.riseup.repository.QuestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.pow

@HiltViewModel
class QuestViewModel @Inject constructor(
    private val questRepository: QuestRepository,
    private val characterRepository: CharacterRepository,
    private val completedQuestRepository: CompletedQuestRepository
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
            questRepository.updateQuest(quest.copy(isAccepted = true))
        }
    }

    fun declineQuest(quest: Quest) {
        viewModelScope.launch {
            questRepository.updateQuest(quest.copy(isAccepted = false))
        }
    }

    fun completeQuest(quest: Quest) {
        character.value?.let {
            viewModelScope.launch {
                questRepository.deleteQuest(quest)
                completedQuestRepository.addCompletedQuest(quest)

                val xpGained = quest.difficulty.xp

                var newLevel = it.level
                var newXp = it.currentXp + xpGained
                var xpForNextLevel = it.xpForNextLevel

                while (newXp >= xpForNextLevel) {
                    newXp -= xpForNextLevel
                    newLevel++
                    xpForNextLevel = calculateXpForNextLevel(newLevel)
                }

                characterRepository.updateCharacter(newLevel, newXp, xpForNextLevel)
            }
        }
    }

    private fun calculateXpForNextLevel(level: Int): Int {
        return when (level) {
            in 1..10 -> 100 + (level - 1) * 40
            in 11..50 -> 500 + (level - 10) * 75
            in 51..100 -> (3000 * 1.04.pow((level - 50).toDouble())).toInt()
            else -> 1000000
        }
    }
}