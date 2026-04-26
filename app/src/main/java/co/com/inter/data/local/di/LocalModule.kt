package co.com.inter.data.local.di

import android.content.Context
import androidx.room.Room
import co.com.inter.data.local.InterDataBase
import co.com.inter.data.local.table.datasource.ITableDataLocalDataSource
import co.com.inter.data.local.table.datasource.TableDataLocalDataSourceImpl
import co.com.inter.data.local.user.datasource.IUserLocalDataSource
import co.com.inter.data.local.user.datasource.UserLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalModule {

    companion object {
        @Singleton
        @Provides
        fun providerRoom(@ApplicationContext context: Context) =
            Room.databaseBuilder(context, InterDataBase::class.java, "InterDataBase").build()

        @Singleton
        @Provides
        fun providerInterDao(dataBase: InterDataBase) = dataBase.getInterDao()
    }

    @Binds
    abstract fun bindUserLocalDataSource(impl: UserLocalDataSourceImpl): IUserLocalDataSource

    @Binds
    abstract fun bindTableLocalDataSource(impl: TableDataLocalDataSourceImpl): ITableDataLocalDataSource

}