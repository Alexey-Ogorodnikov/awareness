/**
 * Domain repository interface for accessing scam data.
 */
package com.alexey.awareness.domain.repository

import com.alexey.awareness.domain.model.Scam
import kotlinx.coroutines.flow.Flow

interface ScamRepository {
    fun getScams(): Flow<List<Scam>>
    fun getScamById(id: Int): Flow<Scam?>
} 