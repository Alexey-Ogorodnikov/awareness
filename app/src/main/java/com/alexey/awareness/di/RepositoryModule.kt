/**
 * Hilt module for providing repository dependencies.
 */
package com.alexey.awareness.di

import com.alexey.awareness.domain.repository.ScamRepository
import com.alexey.awareness.data.repository.ScamRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindScamRepository(
        impl: ScamRepositoryImpl
    ): ScamRepository
} 