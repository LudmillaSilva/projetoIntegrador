package com.ludmilla.integratorproject.data.repository

import androidx.lifecycle.LiveData
import com.ludmilla.integratorproject.data.dao.FavoriteDao
import com.ludmilla.integratorproject.data.model.Favorite
import com.ludmilla.integratorproject.data.response.GenreResp
import io.reactivex.Single

class FavoriteRepositoryImpl(private val favoriteDao: FavoriteDao): FavoriteRepository{

    override fun getAll(): LiveData<List<Favorite>> {
        return favoriteDao.getAll()
    }

    override fun getGenreById(genreId: String): LiveData<List<Favorite>> {
        return favoriteDao.getAllByGenre(genreId)
    }

    override suspend fun save(favorite: Favorite) {
        favoriteDao.save(favorite)
    }

    override suspend fun delete(favorite: Favorite) {
        favoriteDao.remove(favorite)
    }

    override suspend fun checkIfIsFavorite(id:Long):LiveData<Boolean> {
        return favoriteDao.checkIfIsFavorite(id)
    }



}