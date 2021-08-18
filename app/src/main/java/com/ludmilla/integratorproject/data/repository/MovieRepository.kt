package com.ludmilla.integratorproject.data.repository

import android.net.Uri
import com.ludmilla.integratorproject.data.response.*
import io.reactivex.Single

interface MovieRepository {
    fun getPopularMovies():Single<List<ResponseMovie>>
    fun getAllGenres(): Single<List<GenreResp>>
    fun getSearchMovie(movieSearch: Uri): Single<List<ResponseMovie>>
    fun getMoviesByGenre(genreId: String): Single<List<ResponseMovie>>
    fun getDetailMovie(movieId: Int): Single<ResponseDetail>
    fun getCast(movieId: Int): Single<ResponseCast>
}