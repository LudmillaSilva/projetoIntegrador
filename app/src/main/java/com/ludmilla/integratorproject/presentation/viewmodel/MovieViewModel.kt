package com.ludmilla.integratorproject.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ludmilla.integratorproject.data.factory.Network
import com.ludmilla.integratorproject.data.repository.MovieRepositoryImpl
import com.ludmilla.integratorproject.data.response.ResponseMovie
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class MovieViewModel: ViewModel() {

    private val movieRepository = MovieRepositoryImpl()
    private val disposable = CompositeDisposable()
    val liveResponseMovie: MutableLiveData<List<ResponseMovie>> = MutableLiveData<List<ResponseMovie>>()

    fun getPopularMovies(){
        movieRepository.getPopularMovies()
            .compose(Network.applySingleTransformer())
            .subscribe({
                liveResponseMovie.value = it
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
