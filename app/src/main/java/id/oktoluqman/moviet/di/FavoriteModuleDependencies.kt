package id.oktoluqman.moviet.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.oktoluqman.moviet.core.domain.usecase.TMDBUseCase

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun tmdbUseCase(): TMDBUseCase
}
