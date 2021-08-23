package com.ludmilla.integratorproject.presentation.fragment

import com.ludmilla.integratorproject.data.response.ResponseMovie

interface ListenerMovies {
    fun loadMoviesWithGenre(genreIds: List<Int>)
    fun getDetailMovie(movie: ResponseMovie)
    fun getCast(movieId: Int)
    fun handleFavorite(movie: ResponseMovie, isChecked: Boolean)
}