package com.ludmilla.integratorproject.data.repository

import androidx.lifecycle.LiveData
import com.ludmilla.integratorproject.data.model.Favorite

interface FavoriteRepository {
    fun getAll(): LiveData<List<Favorite>>
    suspend fun save(favorite:Favorite)
    suspend fun delete(favorite: Favorite)
    suspend fun checkIfIsFavorite(id:Long):LiveData<Boolean>
}