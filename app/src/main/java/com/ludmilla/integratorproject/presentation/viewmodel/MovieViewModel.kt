package com.ludmilla.integratorproject.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ludmilla.integratorproject.data.factory.Network
import com.ludmilla.integratorproject.data.repository.MovieRepositoryImpl
import com.ludmilla.integratorproject.data.response.GenreResp
import com.ludmilla.integratorproject.data.response.ResponseMovie
import com.ludmilla.integratorproject.domain.Genre
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class MovieViewModel: ViewModel() {

    private val movieRepository = MovieRepositoryImpl()
    private val disposable = CompositeDisposable()
    val liveResponseMovie: MutableLiveData<List<ResponseMovie>> = MutableLiveData<List<ResponseMovie>>()
    val liveGenreResp: MutableLiveData<List<GenreResp>> = MutableLiveData<List<GenreResp>>()
    val liveResponseSearch: MutableLiveData<List<ResponseMovie>> = MutableLiveData<List<ResponseMovie>>()
    val liveResponseGenreByMovie: MutableLiveData<List<ResponseMovie>> = MutableLiveData<List<ResponseMovie>>()

    fun getPopularMovies(){
        movieRepository.getPopularMovies()
            .compose(Network.applySingleTransformer())
            .subscribe({
                liveResponseMovie.value = it
            },{
                print(it.message)
            }).addToDispose()

    }

    fun getGenres(){
        movieRepository.getAllGenres()
            .compose(Network.applySingleTransformer())
            .subscribe({
                liveGenreResp.value = it
            },{
                print(it.message)
            }).addToDispose()
    }

    fun getSearch(movieSearch: Uri){
        movieRepository.getSearchMovie(movieSearch)
            .compose(Network.applySingleTransformer())
            .subscribe({
                liveResponseSearch.value = it
            },{
                print(it.message)
            }).addToDispose()
    }

    fun getMovieByGenre(genreId: String){
        movieRepository.getMoviesByGenre(genreId)
            .compose(Network.applySingleTransformer())
            .subscribe({
                liveResponseGenreByMovie.value = it
            },{
                print(it.message)
            }).addToDispose()
    }


    private fun Disposable.addToDispose(): Disposable = apply { disposable.add(this) }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}
