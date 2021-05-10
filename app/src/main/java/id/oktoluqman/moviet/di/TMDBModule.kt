package id.oktoluqman.moviet.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.oktoluqman.moviet.data.source.TMDBDataSource
import id.oktoluqman.moviet.data.source.TMDBRepository
import id.oktoluqman.moviet.data.source.remote.TMDBRemoteDataSource
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class TMDBModule {
    @Provides
    @Inject
    fun provideTMDBDataSource(remote: TMDBRemoteDataSource): TMDBDataSource {
        return TMDBRepository(remote)
    }
}