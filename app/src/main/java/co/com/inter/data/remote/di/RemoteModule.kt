package co.com.inter.data.remote.di

import co.com.inter.BuildConfig.BASE_URL
import co.com.inter.data.remote.location.datasource.ILocationRemoteDataSource
import co.com.inter.data.remote.location.datasource.LocationRemoteDataSourceImpl
import co.com.inter.data.remote.login.datasource.ILoginRemoteDataSource
import co.com.inter.data.remote.login.datasource.LoginRemoteDataSourceImpl
import co.com.inter.data.remote.service.InterService
import co.com.inter.data.remote.sync.datasource.ISyncDataRemoteDataSource
import co.com.inter.data.remote.sync.datasource.SyncDataRemoteDataSourceImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    fun interService(retrofit: Retrofit): InterService {
        return retrofit.create(InterService::class.java)
    }

    @Singleton
    @Provides
    fun providerLoginRemoteDataSource(
        interService: InterService
    ): ILoginRemoteDataSource = LoginRemoteDataSourceImpl(
        interService = interService
    )

    @Singleton
    @Provides
    fun providerSyncDataRemoteDataSource(
        interService: InterService
    ): ISyncDataRemoteDataSource = SyncDataRemoteDataSourceImpl(
        interService = interService
    )

    @Singleton
    @Provides
    fun providerLocationRemoteDataSource(
        interService: InterService
    ): ILocationRemoteDataSource = LocationRemoteDataSourceImpl(
        interService = interService
    )

}