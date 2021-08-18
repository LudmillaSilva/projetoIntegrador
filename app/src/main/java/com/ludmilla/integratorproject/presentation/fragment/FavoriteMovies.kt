package com.ludmilla.integratorproject.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.ludmilla.integratorproject.R
import com.ludmilla.integratorproject.data.model.Favorite
import com.ludmilla.integratorproject.data.response.ResponseMovie
import com.ludmilla.integratorproject.domain.Movie
import com.ludmilla.integratorproject.presentation.adapter.GenreAdapter
import com.ludmilla.integratorproject.presentation.adapter.MoviesAdapter
import com.ludmilla.integratorproject.presentation.viewmodel.FavoritesViewModel
import com.ludmilla.integratorproject.presentation.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movies.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FavoriteMovies : Fragment(), ListenerMovies{

    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var rvGenre: RecyclerView
    private lateinit var rvMovies: RecyclerView
    val viewModelFavorites: FavoritesViewModel by sharedViewModel()
    val listener: ListenerMovies? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       super.onViewCreated(view, savedInstanceState)

       rvGenre = view.findViewById(R.id.rvGenre)
       rvMovies = view.findViewById(R.id.rvMovies)
 //      viewModelFavorites = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)
       genreAdapter = GenreAdapter(context = view.context, listener = this)
       moviesAdapter = MoviesAdapter(context = view.context, listener = this)
       rvGenre.adapter = genreAdapter
       rvMovies.adapter = moviesAdapter

       initRequests()
       initObservers()


    }


    private fun  initRequests(){
        viewModelFavorites.getAllFavorites()
    }

    private fun initObservers(){
        getFavoriteObserver()
    }

    private fun getFavoriteObserver(){
        viewModelFavorites.liveResponseMovie.observe(viewLifecycleOwner,{favorite ->
            favorite?.let {
                val responseMovies: List<ResponseMovie> = it.map { ResponseMovie(it.id.toInt(),it.img,it.title,it.average,
                    it.genreIds!!.split(",")
                        .toList().map {
                            if(it.isNotBlank()){
                                it.toInt()
                            }
                            0
                        },true)}
                moviesAdapter.listmovie.clear()
                moviesAdapter.listmovie.addAll(responseMovies)
                moviesAdapter.notifyDataSetChanged()
            }
        })
    }


    fun onFavoriteClickedListener(movie: Movie, isChecked: Boolean) {
        if (!isChecked) {
            movie.isFavorite = false

        }
    }

    override fun loadMoviesWithGenre(genreIds: List<Int>) {
        TODO("Not yet implemented")
    }

    override fun getDetailMovie(movieId: Int) {
        TODO("Not yet implemented")
    }

    override fun getCast(movieId: Int) {
        TODO("Not yet implemented")
    }

    override fun handleFavorite(movie: ResponseMovie, isChecked: Boolean) {
        val favorite = Favorite(movie.id.toLong()
            ,movie.poster,
            movie.title,
            movie.average,
            movie.genreIds.joinToString(","))
        if(isChecked){
            viewModelFavorites.saveFavorite(favorite)
        }else{
            viewModelFavorites.deleteFavorite(favorite)
        }
    }
}