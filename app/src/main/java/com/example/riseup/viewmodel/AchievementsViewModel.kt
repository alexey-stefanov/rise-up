package com.example.riseup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.riseup.model.achievement.Achievement
import com.example.riseup.repository.AchievementRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AchievementsViewModel @Inject constructor(
    achievementRepository: AchievementRepository
) : ViewModel(){
    val achievements: LiveData<List<Achievement>?> = achievementRepository.getAllAchievements().asLiveData()
}