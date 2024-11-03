package com.example.riseup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.riseup.model.character.Character
import com.example.riseup.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    characterRepository: CharacterRepository
) : ViewModel() {
    val character: LiveData<Character?> = characterRepository.getCharacter().asLiveData()
}