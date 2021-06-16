package id.oktoluqman.moviet

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import id.oktoluqman.moviet.core.di.AppModule
import id.oktoluqman.moviet.core.utils.TMDBConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
@Suppress("unused")
class FakeAppModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClientProvider.instance
    }

    @Provides
    @Inject
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(TMDBConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}
