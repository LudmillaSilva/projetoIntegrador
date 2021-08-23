package com.ludmilla.integratorproject.presentation.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.ludmilla.integratorproject.R
import com.ludmilla.integratorproject.data.model.Favorite
import com.ludmilla.integratorproject.data.response.ResponseMovie
import com.ludmilla.integratorproject.domain.Movie
import com.ludmilla.integratorproject.presentation.DetailsActivity
import com.ludmilla.integratorproject.presentation.adapter.CastAdapter
import com.ludmilla.integratorproject.presentation.adapter.GenreAdapter
import com.ludmilla.integratorproject.presentation.adapter.MoviesAdapter
import com.ludmilla.integratorproject.presentation.error.ErrorEntity
import com.ludmilla.integratorproject.presentation.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movies.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularMovies : Fragment(), ListenerMovies {


    lateinit var moviesAdapter: MoviesAdapter
    lateinit var genreAdapter: GenreAdapter

    private val movieViewModel: MovieViewModel by sharedViewModel()
    private var movieSearched: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieSearched = it.getString(ARG_PARAM)
        }
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

        val rvmovies = view.findViewById<RecyclerView>(R.id.rvMovies)
        val rvgenre = view.findViewById<RecyclerView>(R.id.rvGenre)
        val itemnotfound = view.findViewById<View>(R.id.itemNotFound)
//        movieViewModel = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)
        moviesAdapter = MoviesAdapter(context = view.context, listener = this)
        rvmovies.adapter = moviesAdapter
        genreAdapter = GenreAdapter(context = view.context, listener = this)
        rvgenre.adapter = genreAdapter

 //       getPopularMovies()
 //       getGenre()
 /*       val movieSearch = "Shrek"
        val movieToURI = movieSearch.toUri()*/
        val movieUri = movieSearched?.toUri()
        if(movieUri!=null){
            searchMovie(movieUri, true)
        }
        val idGenre = "Animação"
        val idGenreToString = idGenre.toString()
        initRequests(idGenreToString)
        initObservers(idGenreToString)

    }

    override fun onResume() {
        super.onResume()
        movieViewModel.liveResponseMovie.removeObservers(this)
        movieViewModel.getPopularMovies()
        popularMovies()
    }

    fun initRequests(genreId:String){
        movieViewModel.getPopularMovies()
        movieViewModel.getGenres()
//        movieViewModel.getSearch(movieSearch)
        movieViewModel.getMovieByGenre(genreId)
    }

    fun initObservers(genreId:String){
        popularMovies()
        getAllGenres()
        errorHandlerObserver()
 //       movieViewModel.getSearch(movieSearch)
        movieByGenreObserver()
        searchObserver()
        movieViewModel.getMovieByGenre(genreId)
    }

    fun searchMovie(query:Uri?, showNotFound: Boolean){
        query?.let {
            if(query?.toString().isNotBlank()){
                movieViewModel.getSearch(it, showNotFound)
            }else if (query?.toString().isBlank()){
                movieViewModel.getPopularMovies()
            }
        }
    }

    private fun searchObserver(){
        movieViewModel.liveResponseSearch.observe(viewLifecycleOwner,{ movieSearched ->
            movieSearched?.let{
                rvMovies.visibility = View.VISIBLE
                moviesAdapter.listmovie.clear()
                moviesAdapter.listmovie.addAll(it)
                moviesAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun movieByGenreObserver(){
        movieViewModel.liveResponseMovieByGenre.observe(viewLifecycleOwner,{ movieByGenre ->
            movieByGenre?.let{
                rvMovies.visibility = View.VISIBLE
                moviesAdapter.listmovie.clear()
                moviesAdapter.listmovie.addAll(it)
                moviesAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun errorHandlerObserver(){
        movieViewModel.liveExceptionHandler.observe(viewLifecycleOwner, { handle ->
            when (handle){
                ErrorEntity.NotFoundError->{
                    ErrorEntity.doOnNotFoundError(itemNotFound,rvMovies)
                }
                ErrorEntity.UnknownError ->{
                    ErrorEntity.doOnUnknownError(this,requireContext())
                    //startActivity(Intent(requireContext(),UnknownErrorActivity::class.java))
                }
            }
        })
    }

    private fun popularMovies() {
        movieViewModel.liveResponseMovie.observe(viewLifecycleOwner,{ movieList ->
            movieList?.let {
                moviesAdapter.listmovie.clear()
                moviesAdapter.listmovie.addAll(it)
                moviesAdapter.notifyDataSetChanged()
            }

        })
    }
    private fun getAllGenres() {
        movieViewModel.liveGenreResp.observe(viewLifecycleOwner,{ genreList ->
            genreList?.let {
                genreAdapter.listgenre.addAll(it)
                genreAdapter.notifyDataSetChanged()
            }

        })
    }

    override fun loadMoviesWithGenre(genreId: List<Int>) {
        movieViewModel.getMovieByGenre(genreId.joinToString(","))
    }

    override fun getDetailMovie(movie: ResponseMovie) {
        val detailMovieId = Intent(requireContext(),DetailsActivity::class.java)
        detailMovieId.putExtra("MOVIE_ID", Movie.parserToMovie(movie))
        startActivity(detailMovieId)
    }

    override fun getCast(movieId: Int) {
        val castMovieId = Intent (requireContext(),DetailsActivity::class.java)
        castMovieId.putExtra("MOVIE_ID", movieId)
        startActivity(castMovieId)

    }

    override fun handleFavorite(movie: ResponseMovie, isChecked: Boolean) {
        val favorite = Favorite(movie.id.toLong()
            ,movie.poster,
            movie.title,
            movie.average,
            movie.genreIds.joinToString(","))
        if(isChecked){
            movieViewModel.saveFavorite(favorite)
        }else{
            movieViewModel.deleteFavorite(favorite)
        }

    }


    companion object{
        private const val ARG_PARAM = "searched"

        @JvmStatic
        fun newInstance(movieSearched: String) =
            PopularMovies().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM, movieSearched)
                }
            }
    }

}



