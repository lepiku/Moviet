package id.oktoluqman.moviet.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.oktoluqman.moviet.domain.repository.TMDBDataSource
import id.oktoluqman.moviet.data.TMDBRepository
import id.oktoluqman.moviet.data.source.local.TMDBLocalDataSource
import id.oktoluqman.moviet.data.source.local.room.MovieDao
import id.oktoluqman.moviet.data.source.local.room.TMDBDatabase
import id.oktoluqman.moviet.data.source.local.room.TvDao
import id.oktoluqman.moviet.data.source.remote.TMDBRemoteDataSource
import id.oktoluqman.moviet.domain.usecase.TMDBInteractor
import id.oktoluqman.moviet.domain.usecase.TMDBUseCase
import id.oktoluqman.moviet.services.TMDBService
import id.oktoluqman.moviet.utils.AppExecutors
import id.oktoluqman.moviet.utils.TMDBConstants
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TMDBModule {
    @Singleton
    @Provides
    fun provideTMDBDataSource(
        remote: TMDBRemoteDataSource,
        local: TMDBLocalDataSource,
        executors: AppExecutors
    ): TMDBDataSource {
        return TMDBRepository(remote, local, executors)
    }

    @Singleton
    @Provides
    fun provideTMDBDatabase(@ApplicationContext appContext: Context): TMDBDatabase {
        return Room.databaseBuilder(
            appContext,
            TMDBDatabase::class.java,
            TMDBConstants.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideTMDBService(retrofit: Retrofit): TMDBService {
        return retrofit.create(TMDBService::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieDao(database: TMDBDatabase): MovieDao {
        return database.MovieDao()
    }

    @Singleton
    @Provides
    fun provideTvDao(database: TMDBDatabase): TvDao {
        return database.TvDao()
    }

    @Singleton
    @Provides
    fun provideAppExecutors(): AppExecutors {
        return AppExecutors()
    }

    @Singleton
    @Provides
    fun provideTMDBUseCase(dataSource: TMDBDataSource): TMDBUseCase {
        return TMDBInteractor(dataSource)
    }
}
