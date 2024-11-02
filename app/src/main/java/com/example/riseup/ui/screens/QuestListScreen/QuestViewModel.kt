package com.example.riseup.ui.screens.QuestListScreen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.riseup.data.model.Quest
import com.example.riseup.data.model.QuestDifficulty
import com.example.riseup.data.model.QuestType
import java.time.LocalDate

class QuestViewModel : ViewModel() {
    private val mutableQuests = mutableStateListOf<Quest>()
    val quests: List<Quest> = mutableQuests

    private var questIdCounter = 0

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
    }

    fun removeQuest(quest: Quest) {
        mutableQuests.remove(quest)
    }
}