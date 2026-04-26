package co.com.inter.di

import android.content.Context
import co.com.inter.data.local.table.datasource.ITableDataLocalDataSource
import co.com.inter.data.local.user.datasource.IUserLocalDataSource
import co.com.inter.data.remote.auth.datasource.IAuthRemoteDataSource
import co.com.inter.data.remote.location.datasource.ILocationRemoteDataSource
import co.com.inter.data.remote.service.InterService
import co.com.inter.data.remote.sync.datasource.ISyncDataRemoteDataSource
import co.com.inter.data.repository.AuthRepositoryImpl
import co.com.inter.data.repository.CheckAppVersionRepositoryImpl
import co.com.inter.data.repository.LocationRepositoryImpl
import co.com.inter.data.repository.TableRepositoryImpl
import co.com.inter.data.repository.SyncDataRepositoryImpl
import co.com.inter.data.repository.UserRepositoryImpl
import co.com.inter.domain.repository.IAuthRepository
import co.com.inter.domain.repository.ICheckAppVersionRepository
import co.com.inter.domain.repository.ILocationRepository
import co.com.inter.domain.repository.ITableRepository
import co.com.inter.domain.repository.ISyncDataRepository
import co.com.inter.domain.repository.IUserRepository
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
        remote: IAuthRemoteDataSource,
        dispatcherIO: CoroutineDispatcher
    ): IAuthRepository = AuthRepositoryImpl(
        remote = remote,
        dispatcherIO = dispatcherIO
    )

    @Singleton
    @Provides
    fun providerSyncDataRepository(
        remote: ISyncDataRemoteDataSource,
        dispatcherIO: CoroutineDispatcher
    ): ISyncDataRepository = SyncDataRepositoryImpl(
        remote = remote,
        dispatcherIO = dispatcherIO
    )

    @Singleton
    @Provides
    fun providerLocationRepository(
        remote: ILocationRemoteDataSource,
        dispatcherIO: CoroutineDispatcher
    ): ILocationRepository = LocationRepositoryImpl(remote = remote, dispatcherIO = dispatcherIO)

    @Singleton
    @Provides
    fun providerUserRepository(
        local: IUserLocalDataSource,
        dispatcherIO: CoroutineDispatcher
    ): IUserRepository = UserRepositoryImpl(local = local, dispatcherIO = dispatcherIO)

    @Singleton
    @Provides
    fun providerTableRepository(
        local: ITableDataLocalDataSource,
        dispatcherIO: CoroutineDispatcher
    ): ITableRepository = TableRepositoryImpl(local = local, dispatcherIO = dispatcherIO)

}