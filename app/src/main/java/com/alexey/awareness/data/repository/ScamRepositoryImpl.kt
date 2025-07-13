/**
 * Data layer implementation of ScamRepository using in-memory sample data.
 */
package com.alexey.awareness.data.repository

import com.alexey.awareness.domain.model.Scam
import com.alexey.awareness.domain.repository.ScamRepository
import com.alexey.awareness.data.sample.sampleScams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ScamRepositoryImpl @Inject constructor() : ScamRepository {
    private val scamsFlow = MutableStateFlow(sampleScams)

    override fun getScams(): Flow<List<Scam>> = scamsFlow

    override fun getScamById(id: Int): Flow<Scam?> = scamsFlow.map { list ->
        list.find { it.id == id }
    }
} 