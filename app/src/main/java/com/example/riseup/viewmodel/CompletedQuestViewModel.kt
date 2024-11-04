package com.example.riseup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.riseup.repository.CompletedQuestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CompletedQuestViewModel @Inject constructor(
    completedQuestRepository: CompletedQuestRepository
) : ViewModel() {

    val completedQuests = completedQuestRepository.getCompletedQuests().asLiveData()
}