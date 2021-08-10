package com.ludmilla.integratorproject.data.repository

import android.net.Uri
import com.ludmilla.integratorproject.data.response.GenreResp
import com.ludmilla.integratorproject.data.response.ResponseGenre
import com.ludmilla.integratorproject.data.response.ResponseMovie
import io.reactivex.Single

interface MovieRepository {
    fun getPopularMovies():Single<List<ResponseMovie>>
    fun getAllGenres(): Single<List<GenreResp>>
    fun getSearchMovie(movieSearch: Uri): Single<List<ResponseMovie>>
    fun getMoviesByGenre(genreId: String): Single<List<ResponseMovie>>
}