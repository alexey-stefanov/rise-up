package com.example.riseup.util

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.example.riseup.model.quest.Quest
import com.example.riseup.model.quest.QuestDifficulty
import com.example.riseup.model.quest.QuestState
import com.example.riseup.model.quest.QuestType
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@SuppressLint("NewApi")
class DailyQuestManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE

    fun shouldInitializeDailyQuests(): Boolean {
        val savedDate = sharedPreferences.getString(LAST_DATE_KEY, null)
        val currentDate = LocalDate.now().format(dateFormatter)

        return if (savedDate != currentDate) {
            sharedPreferences.edit().putString(LAST_DATE_KEY, currentDate).apply()
            true
        } else {
            false
        }
    }

    fun getDailyQuests(): List<Quest> {
        return listOf(
            Quest(id = 0, name = "Сделать зарядку", type = QuestType.DAILY, difficulty = QuestDifficulty.EASY, state = QuestState.Accepted),
            Quest(id = 0, name = "Прочитать книгу", type = QuestType.DAILY, difficulty = QuestDifficulty.MEDIUM, state = QuestState.Accepted),
            Quest(id = 0, name = "Медитировать", type = QuestType.DAILY, difficulty = QuestDifficulty.EASY, state = QuestState.Accepted),
            Quest(id = 0, name = "Сходить на пробежку", type = QuestType.DAILY, difficulty = QuestDifficulty.HARD, state = QuestState.Accepted),
            Quest(id = 0, name = "Выпить воды", type = QuestType.DAILY, difficulty = QuestDifficulty.MEDIUM, state = QuestState.Accepted)
        )
    }

    companion object {
        private const val LAST_DATE_KEY = "last_date_daily_quests"
    }
}