package id.oktoluqman.moviet.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.oktoluqman.moviet.BuildConfig
import id.oktoluqman.moviet.utils.TMDBConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            clientBuilder.addInterceptor(loggingInterceptor)
        }

        return clientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(TMDBConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}
