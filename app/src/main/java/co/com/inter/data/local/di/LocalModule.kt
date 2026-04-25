package co.com.inter.data.local.di

import android.content.Context
import androidx.room.Room
import co.com.inter.data.local.InterDataBase
import co.com.inter.data.local.dao.InterDao
import co.com.inter.data.local.repository.SaveUserRepositoryImpl
import co.com.inter.domain.repository.ISaveUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun providerRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, InterDataBase::class.java, "InterDataBase").build()

    @Singleton
    @Provides
    fun providerInterDao(dataBase: InterDataBase) = dataBase.getInterDao()


    @Singleton
    @Provides
    fun providerSaveUserRepository(dao: InterDao): ISaveUserRepository = SaveUserRepositoryImpl(dao)
}