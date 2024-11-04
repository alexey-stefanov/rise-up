package com.example.riseup.model.quest

sealed class QuestState(val state: String) {
    object New : QuestState(STATE_NEW)
    object Accepted : QuestState(STATE_ACCEPTED)
    data class Completed(val completionDate: Long) : QuestState(STATE_COMPLETED)

    companion object {
        const val STATE_NEW = "new"
        const val STATE_ACCEPTED = "accepted"
        const val STATE_COMPLETED = "completed"
    }
}