package io.neoattitude.defio.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.neoattitude.defio.data.entity.Token

@Dao
interface TokenDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(token: Token?)

    @Query("select * from token limit 1")
    fun fetchLast(): Token?

    @Query("select * from token")
    fun fetchAll(): LiveData<List<Token>>?

    @Query("delete from token")
    suspend fun deleteAll()
}