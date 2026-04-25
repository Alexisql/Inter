package co.com.inter.di

import co.com.inter.data.local.sync.datasource.ISchemeLocalDataSource
import co.com.inter.data.remote.sync.datasource.ISchemeRemoteDataSource
import co.com.inter.data.repository.SyncDataRepositoryImpl
import co.com.inter.domain.repository.ISyncDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providerSyncDataRepository(
        local: ISchemeLocalDataSource,
        remote: ISchemeRemoteDataSource
    ): ISyncDataRepository = SyncDataRepositoryImpl(
        local = local,
        remote = remote
    )

}