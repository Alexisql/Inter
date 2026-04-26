package co.com.inter.di

import co.com.inter.data.repository.AuthRepositoryImpl
import co.com.inter.data.repository.CheckAppVersionRepositoryImpl
import co.com.inter.data.repository.LocationRepositoryImpl
import co.com.inter.data.repository.SyncDataRepositoryImpl
import co.com.inter.data.repository.TableRepositoryImpl
import co.com.inter.data.repository.UserRepositoryImpl
import co.com.inter.domain.repository.IAuthRepository
import co.com.inter.domain.repository.ICheckAppVersionRepository
import co.com.inter.domain.repository.ILocationRepository
import co.com.inter.domain.repository.ISyncDataRepository
import co.com.inter.domain.repository.ITableRepository
import co.com.inter.domain.repository.IUserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    companion object {
        @Singleton
        @Provides
        fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO
    }

    @Binds
    abstract fun bindCheckAppVersionRepository(impl: CheckAppVersionRepositoryImpl): ICheckAppVersionRepository

    @Binds
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): IAuthRepository

    @Binds
    abstract fun bindSyncDataRepository(impl: SyncDataRepositoryImpl): ISyncDataRepository

    @Binds
    abstract fun bindLocationRepository(impl: LocationRepositoryImpl): ILocationRepository

    @Binds
    abstract fun bindUserRepository(impl: UserRepositoryImpl): IUserRepository

    @Binds
    abstract fun bindTableRepository(impl: TableRepositoryImpl): ITableRepository


}