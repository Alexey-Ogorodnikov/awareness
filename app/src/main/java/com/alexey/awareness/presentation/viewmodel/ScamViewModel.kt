/**
 * ViewModel for managing scam data and exposing it to the UI layer.
 */
package com.alexey.awareness.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexey.awareness.domain.model.Scam
import com.alexey.awareness.domain.repository.ScamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.map

@HiltViewModel
class ScamViewModel @Inject constructor(
    private val repository: ScamRepository
) : ViewModel() {
    val scamList: StateFlow<List<Scam>> = repository.getScams()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun getScamById(id: Int): StateFlow<Scam?> = repository.getScamById(id)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
}