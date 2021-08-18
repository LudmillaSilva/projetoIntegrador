package com.ludmilla.integratorproject.presentation.fragment

interface ListenerMovies {
    fun loadMoviesWithGenre(genreIds: List<Int>)
    fun getDetailMovie(movieId: Int)
    fun getCast(movieId: Int)
}