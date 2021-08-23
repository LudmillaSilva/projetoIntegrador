package com.ludmilla.integratorproject.data.remotesource

import android.net.Uri
import com.ludmilla.integratorproject.data.response.*
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteSource {

    @GET("movie/popular")
    fun getPopularMovies(): Single<ResponseMovies>

    @GET("genre/movie/list")
    fun getAllGenres(): Single<ResponseGenre>

    @GET("search/movie")
    fun getSearchMovie(@Query("query") movieSearch: Uri): Single<ResponseMovies>

    @GET("discover/movie")
    fun getMoviesByGenre(@Query("with_genres", encoded = true) genreId: String): Single<ResponseMovies>

    @GET("movie/{movie_id}")
    fun getDetailMovie(@Path("movie_id") movieId: Int): Single<ResponseDetail>

    @GET("movie/{movie_id}/credits")
    fun getCast(@Path("movie_id") movieId: Int): Single<ResponseCast>

    @GET("movie/{movie_id}/release_dates")
    fun getParentalGuide(@Path("movie_id")movieId: Int):Single<ResponseCertification>


/*
    @GET("movie/popular")
    fun getPopularMovies(): Single<ResponseMovies>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: Int): Single<ResponseDetail>

    @GET("search/movie")
    fun searchForMovie(@Query("movieSearched") query: String): Single<ResponseMovies>

    @GET("movie/{movie_id}/credits")
    fun getCast(@Path("movie_id") movieId: Int): Single<ResponseCast>


    @GET("movie/{movie_id}/release_dates")
    fun getCertification(@Path("movie_id") movieId: Int): Single<ResponseCertification>

    */
}