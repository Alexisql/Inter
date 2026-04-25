package co.com.inter.di

import android.content.Context
import co.com.inter.data.local.login.datasource.ILoginLocalDataSource
import co.com.inter.data.local.sync.datasource.ISyncDataLocalDataSource
import co.com.inter.data.remote.location.datasource.ILocationRemoteDataSource
import co.com.inter.data.remote.login.datasource.ILoginRemoteDataSource
import co.com.inter.data.remote.service.InterService
import co.com.inter.data.remote.sync.datasource.ISyncDataRemoteDataSource
import co.com.inter.data.repository.CheckAppVersionRepositoryImpl
import co.com.inter.data.repository.LocationRepositoryImpl
import co.com.inter.data.repository.LoginRepositoryImpl
import co.com.inter.data.repository.SyncDataRepositoryImpl
import co.com.inter.domain.repository.ICheckAppVersionRepository
import co.com.inter.domain.repository.ILocationRepository
import co.com.inter.domain.repository.ILoginRepository
import co.com.inter.domain.repository.ISyncDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Singleton
    @Provides
    fun providerCheckAppVersionRepository(
        @ApplicationContext context: Context,
        interService: InterService,
        dispatcherIO: CoroutineDispatcher
    ): ICheckAppVersionRepository = CheckAppVersionRepositoryImpl(
        context = context,
        interService = interService,
        dispatcherIO = dispatcherIO
    )

    @Singleton
    @Provides
    fun providerLoginRepository(
        local: ILoginLocalDataSource,
        remote: ILoginRemoteDataSource,
        dispatcherIO: CoroutineDispatcher
    ): ILoginRepository = LoginRepositoryImpl(
        local = local,
        remote = remote,
        dispatcherIO = dispatcherIO
    )

    @Singleton
    @Provides
    fun providerSyncDataRepository(
        local: ISyncDataLocalDataSource,
        remote: ISyncDataRemoteDataSource,
        dispatcherIO: CoroutineDispatcher
    ): ISyncDataRepository = SyncDataRepositoryImpl(
        local = local,
        remote = remote,
        dispatcherIO = dispatcherIO
    )

    @Singleton
    @Provides
    fun providerLocationRepository(
        remote: ILocationRemoteDataSource,
        dispatcherIO: CoroutineDispatcher
    ): ILocationRepository = LocationRepositoryImpl(remote = remote, dispatcherIO = dispatcherIO)

}