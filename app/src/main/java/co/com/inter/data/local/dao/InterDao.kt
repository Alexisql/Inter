package co.com.inter.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.com.inter.data.local.sync.entity.SchemeEntity
import co.com.inter.data.local.user.entity.UserEntity

@Dao
interface InterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScheme(scheme: List<SchemeEntity>)

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun getUser(): UserEntity?
}