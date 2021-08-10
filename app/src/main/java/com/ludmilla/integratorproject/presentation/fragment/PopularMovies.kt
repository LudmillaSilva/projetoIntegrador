package com.ludmilla.integratorproject.presentation.fragment

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
import com.ludmilla.integratorproject.presentation.adapter.GenreAdapter
import com.ludmilla.integratorproject.presentation.adapter.MoviesAdapter
import com.ludmilla.integratorproject.presentation.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movies.*

class PopularMovies : Fragment(), ListenerMovies {


    lateinit var moviesAdapter: MoviesAdapter
    lateinit var genreAdapter: GenreAdapter
    private lateinit var movieViewModel: MovieViewModel
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
        movieViewModel = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)
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
            searchMovie(movieUri)
        }
        val idGenre = "Animação"
        val idGenreToString = idGenre.toString()
        initRequests(idGenreToString)
        initObservers(idGenreToString)

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
 //       movieViewModel.getSearch(movieSearch)
        movieByGenreObserver()
        searchObserver()
        movieViewModel.getMovieByGenre(genreId)
    }

    fun searchMovie(query:Uri?){
        query?.let {
            if(query?.toString().isNotBlank()){
                movieViewModel.getSearch(it)
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

    private fun popularMovies() {
        movieViewModel.liveResponseMovie.observe(viewLifecycleOwner,{ movieList ->
            movieList?.let {
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



