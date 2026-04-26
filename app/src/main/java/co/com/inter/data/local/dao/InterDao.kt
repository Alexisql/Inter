package co.com.inter.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.com.inter.data.local.table.entity.TableEntity
import co.com.inter.data.local.user.entity.UserEntity

@Dao
interface InterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun getUser(): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTable(scheme: List<TableEntity>)

    @Query("SELECT * FROM scheme ORDER BY id")
    fun getAllScheme(): PagingSource<Int, TableEntity>
}