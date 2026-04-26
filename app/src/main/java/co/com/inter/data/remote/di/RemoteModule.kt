package co.com.inter.data.remote.di

import co.com.inter.BuildConfig.BASE_URL
import co.com.inter.data.remote.auth.datasource.AuthRemoteDataSourceImpl
import co.com.inter.data.remote.auth.datasource.IAuthRemoteDataSource
import co.com.inter.data.remote.location.datasource.ILocationRemoteDataSource
import co.com.inter.data.remote.location.datasource.LocationRemoteDataSourceImpl
import co.com.inter.data.remote.service.InterService
import co.com.inter.data.remote.sync.datasource.ISyncDataRemoteDataSource
import co.com.inter.data.remote.sync.datasource.SyncDataRemoteDataSourceImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
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
abstract class RemoteModule {

    companion object {
        val json = Json {
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
    }

    @Binds
    abstract fun bindAuthRemoteDataSource(impl: AuthRemoteDataSourceImpl): IAuthRemoteDataSource

    @Binds
    abstract fun bindSyncRemoteDataSource(impl: SyncDataRemoteDataSourceImpl): ISyncDataRemoteDataSource

    @Binds
    abstract fun bindLocationRemoteDataSource(impl: LocationRemoteDataSourceImpl): ILocationRemoteDataSource


}