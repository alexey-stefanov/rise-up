package com.example.riseup.ui.screens.quest_list

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.riseup.data.model.character.Character
import com.example.riseup.data.model.quest.Quest
import com.example.riseup.data.model.quest.QuestDifficulty
import com.example.riseup.data.model.quest.QuestType

class QuestViewModel : ViewModel() {
    private val mutableQuests = mutableStateListOf<Quest>()
    val quests: List<Quest> = mutableQuests

    private var questIdCounter = 0

    var character = Character()
        private set

    //private var lastDateChecked: LocalDate = LocalDate.now()

    init {
        checkAndInitializeDailyQuests()
    }

    private fun checkAndInitializeDailyQuests() {
        //val currentDate = LocalDate.now()
        //if(lastDateChecked != currentDate) {
            //lastDateChecked = currentDate
            initializeDailyQuests()
        //}
    }

    private fun initializeDailyQuests() {
        mutableQuests.removeAll { it.type == QuestType.DAILY }
        // Placeholder
        val dailyQuests = listOf(
            Quest(id = questIdCounter++, name = "Сделать зарядку", type = QuestType.DAILY, difficulty = QuestDifficulty.EASY),
            Quest(id = questIdCounter++, name = "Прочитать книгу", type = QuestType.DAILY, difficulty = QuestDifficulty.EASY),
            Quest(id = questIdCounter++, name = "Медитировать", type = QuestType.DAILY, difficulty = QuestDifficulty.MEDIUM),
            Quest(id = questIdCounter++, name = "Сходить на пробежку", type = QuestType.DAILY, difficulty = QuestDifficulty.HARD),
            Quest(id = questIdCounter++, name = "Выпить воды", type = QuestType.DAILY, difficulty = QuestDifficulty.EASY)
        )
        mutableQuests.addAll(dailyQuests)
    }

    private fun addXp(xp: Int) {
        var newXp = character.currentXp + xp
        var newLevel = character.level
        var xpForNextLevel = character.xpForNextLevel

        while (newXp >= xpForNextLevel) {
            newXp -= xpForNextLevel
            newLevel++
            xpForNextLevel = calculateXpForNextLevel(newLevel)
        }

        character = character.copy(
            level = newLevel,
            currentXp = newXp,
            xpForNextLevel = xpForNextLevel
        )
    }

    private fun calculateXpForNextLevel(level: Int): Int {
        return 100 + (level - 1) * 50
    }

    fun addQuest(name: String, difficulty: QuestDifficulty) {
        val newQuest = Quest(
            id = questIdCounter++,
            name = name,
            type = QuestType.REGULAR,
            difficulty = difficulty
        )
        mutableQuests.add(newQuest)
    }

    fun acceptQuest(quest: Quest) {
        val index = mutableQuests.indexOf(quest)
        if(index != -1) {
            mutableQuests[index] = quest.copy(isAccepted = true)
        }
    }

    fun declineQuest(quest: Quest) {
        val index = mutableQuests.indexOf(quest)
        if(index != -1) {
            mutableQuests[index] = quest.copy(isAccepted = false)
        }
    }

    fun completeQuest(quest: Quest) {
        removeQuest(quest)
        addXp(quest.xp)
    }

    fun removeQuest(quest: Quest) {
        mutableQuests.remove(quest)
    }
}