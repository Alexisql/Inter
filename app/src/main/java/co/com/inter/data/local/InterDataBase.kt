package co.com.inter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import co.com.inter.data.local.dao.InterDao
import co.com.inter.data.local.sync.entity.SchemeEntity
import co.com.inter.data.local.login.entity.UserEntity

@Database(entities = [UserEntity::class, SchemeEntity::class], version = 1)
abstract class InterDataBase : RoomDatabase() {
    abstract fun getInterDao(): InterDao
}