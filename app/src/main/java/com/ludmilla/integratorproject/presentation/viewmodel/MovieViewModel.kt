package com.ludmilla.integratorproject.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ludmilla.integratorproject.data.dao.FavoriteDao
import com.ludmilla.integratorproject.data.factory.Network
import com.ludmilla.integratorproject.data.model.Favorite
import com.ludmilla.integratorproject.data.repository.MovieRepositoryImpl
import com.ludmilla.integratorproject.data.response.*
import com.ludmilla.integratorproject.domain.Movie
import com.ludmilla.integratorproject.domain.MovieDetail
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepositoryImpl,
                     private val favoriteDao: FavoriteDao): ViewModel() {


    private val disposable = CompositeDisposable()
    val liveResponseMovie: MutableLiveData<List<ResponseMovie>> = MutableLiveData<List<ResponseMovie>>()
    val liveGenreResp: MutableLiveData<List<GenreResp>> = MutableLiveData<List<GenreResp>>()
    val liveResponseSearch: MutableLiveData<List<ResponseMovie>> = MutableLiveData<List<ResponseMovie>>()
    val liveResponseMovieByGenre: MutableLiveData<List<ResponseMovie>> = MutableLiveData<List<ResponseMovie>>()
    val liveResponseDetailMovie: MutableLiveData<ResponseDetail> = MutableLiveData<ResponseDetail>()
    val liveResponseCast: MutableLiveData<List<CastResp>> = MutableLiveData<List<CastResp>>()

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
                liveResponseMovieByGenre.value = it
            },{
                print(it.message)
            }).addToDispose()
    }

    fun getDetailMovie(movieId: Int){
        movieRepository.getDetailMovie(movieId)
            .compose(Network.applySingleTransformer())
            .subscribe({
                liveResponseDetailMovie.value = it
            },{
                print(it.message)
            }).addToDispose()
    }

    fun getCast(movieId: Int){
        movieRepository.getCast(movieId)
            .compose(Network.applySingleTransformer())
            .subscribe({
                liveResponseCast.value = it.cast
            },{
                print(it.message)
            }).addToDispose()
    }

   // fun getFavoriteMovie(movie: Movie){
    //    movieRepository.

 //   }

    fun deleteFavorite(favorite: Favorite){
        viewModelScope.launch {
            favoriteDao.remove(favorite)
        }
    }

    fun saveFavorite(favorite:Favorite){
        viewModelScope.launch {
            favoriteDao.save(favorite)
        }
    }

    private fun Disposable.addToDispose(): Disposable = apply { disposable.add(this) }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}
