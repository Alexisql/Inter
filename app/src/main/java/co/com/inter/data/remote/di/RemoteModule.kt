package co.com.inter.data.remote.di

import android.content.Context
import co.com.inter.BuildConfig.BASE_URL
import co.com.inter.data.remote.repository.AppVersionRepositoryImpl
import co.com.inter.data.remote.service.InterService
import co.com.inter.domain.repository.IAppVersionRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    @Singleton
    @Provides
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()
    }

    @Singleton
    @Provides
    fun interceptorOkHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Singleton
    @Provides
    fun interService(retrofit: Retrofit): InterService {
        return retrofit.create(InterService::class.java)
    }

    @Singleton
    @Provides
    fun providerAppVersionRepository(
        @ApplicationContext context: Context,
        interService: InterService,
        dispatcherIO: CoroutineDispatcher
    ): IAppVersionRepository = AppVersionRepositoryImpl(
        context = context,
        dispatcherIO = dispatcherIO,
        interService = interService
    )
}