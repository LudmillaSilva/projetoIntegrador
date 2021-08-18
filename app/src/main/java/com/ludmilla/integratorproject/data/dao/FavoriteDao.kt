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

    @Query("SELECT * from table_favorites where genreIds= :genreIds")
    fun getAllByGenre(genreIds: String) :LiveData<List<Favorite>>

    @Query("SELECT EXISTS (SELECT * FROM TABLE_FAVORITES WHERE id =:id)")
    fun checkIfFavoriteExists(id: Long) : Boolean


}