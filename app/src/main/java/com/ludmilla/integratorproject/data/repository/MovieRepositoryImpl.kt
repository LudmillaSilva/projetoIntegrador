package com.ludmilla.integratorproject.data.repository
import android.net.Uri
import com.ludmilla.integratorproject.data.dao.FavoriteDao
import com.ludmilla.integratorproject.data.factory.Network
import com.ludmilla.integratorproject.data.remotesource.RemoteSource
import com.ludmilla.integratorproject.data.response.*
import io.reactivex.Single

class MovieRepositoryImpl(private val favoriteDao: FavoriteDao):MovieRepository {

    private val remoteSource: RemoteSource = Network.getRemoteSource()

    override fun getPopularMovies(): Single<List<ResponseMovie>> {
        return remoteSource.getPopularMovies()
            .map {
                    it.results.forEach { responseMovie ->
                        if(favoriteDao.checkIfFavoriteExists(responseMovie.id.toLong()))
                            responseMovie.isFavorite= true
                        }
                    it.results
                 }
    }

    override fun getAllGenres(): Single<List<GenreResp>> {
        return remoteSource.getAllGenres()
            .map { it.genres }
    }

    override fun getSearchMovie(movieSearch:Uri): Single<List<ResponseMovie>> {
        return remoteSource.getSearchMovie(movieSearch)
            .map { it.results }
    }

    override fun getMoviesByGenre(genreId: String): Single<List<ResponseMovie>> {
        return remoteSource.getMoviesByGenre(genreId)
            .map { it.results }
    }

    override fun getDetailMovie(movieId: Int): Single<ResponseDetail> {
        return remoteSource.getDetailMovie(movieId)
            .map {it}
    }

    override fun getCast(movieId: Int): Single<ResponseCast> {
        return remoteSource.getCast(movieId)
            .map {it}
    }

    override fun getParentalGuide(movieId: Int): Single<ReleaseDateResponse> {
        return remoteSource.getParentalGuide(movieId).map {
            it.results.filter {certification -> certification.region == "BR" }.random().release_date.random()
        }
    }



}