package com.ludmilla.integratorproject.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ludmilla.integratorproject.data.model.Favorite

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(favorite : Favorite)

    @Delete
    suspend fun remove (favorite : Favorite)

    @Query("SELECT * from table_favorites")
    fun getAll(): LiveData<List<Favorite>>


}