package com.example.riseup.util

import androidx.room.TypeConverter
import com.example.riseup.model.quest.QuestState
import com.google.gson.Gson
import com.google.gson.JsonObject

class QuestStateConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromQuestState(state: QuestState): String {
        return when (state) {
            is QuestState.New -> QuestState.STATE_NEW
            is QuestState.Accepted -> QuestState.STATE_ACCEPTED
            is QuestState.Completed -> gson.toJson(state)
        }
    }

    @TypeConverter
    fun toQuestState(value: String): QuestState {
        return when (value) {
            QuestState.STATE_NEW -> QuestState.New
            QuestState.STATE_ACCEPTED -> QuestState.Accepted
            else -> {
                try {
                    val jsonObject = gson.fromJson(value, JsonObject::class.java)
                    val completionDate = jsonObject.get("completionDate").asLong
                    QuestState.Completed(completionDate)
                } catch (_: Exception) {
                    QuestState.New
                }
            }
        }
    }
}