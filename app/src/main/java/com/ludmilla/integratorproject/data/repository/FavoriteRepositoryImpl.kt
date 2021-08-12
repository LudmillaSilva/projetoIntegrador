package com.ludmilla.integratorproject.data.repository

import androidx.lifecycle.LiveData
import com.ludmilla.integratorproject.data.dao.FavoriteDao
import com.ludmilla.integratorproject.data.model.Favorite

class FavoriteRepositoryImpl(private val favoriteDao: FavoriteDao): FavoriteRepository{

    override fun getAll(): LiveData<List<Favorite>> {
        return favoriteDao.getAll()
    }

    override suspend fun save(favorite: Favorite) {
        favoriteDao.save(favorite)
    }

    override suspend fun delete(favorite: Favorite) {
        favoriteDao.remove(favorite)
    }

}