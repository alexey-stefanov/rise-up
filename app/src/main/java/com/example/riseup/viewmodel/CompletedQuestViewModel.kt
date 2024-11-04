package com.example.riseup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.riseup.model.quest.Quest
import com.example.riseup.model.quest.QuestState
import com.example.riseup.repository.CompletedQuestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CompletedQuestViewModel @Inject constructor(
    completedQuestRepository: CompletedQuestRepository
) : ViewModel() {

    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    val groupedCompletedQuests: LiveData<Map<String, List<Quest>>> =
        completedQuestRepository.getCompletedQuests().map { quests ->
            quests
                .groupBy { dateFormatter.format((it.state as QuestState.Completed).completionDate) }
        }.asLiveData()
}