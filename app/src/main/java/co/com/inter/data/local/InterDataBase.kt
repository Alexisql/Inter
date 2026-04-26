package co.com.inter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import co.com.inter.data.local.dao.InterDao
import co.com.inter.data.local.table.entity.TableEntity
import co.com.inter.data.local.user.entity.UserEntity

@Database(entities = [UserEntity::class, TableEntity::class], version = 1)
abstract class InterDataBase : RoomDatabase() {
    abstract fun getInterDao(): InterDao
}