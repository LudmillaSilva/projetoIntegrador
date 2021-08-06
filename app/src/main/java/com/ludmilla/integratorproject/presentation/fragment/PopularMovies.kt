package com.ludmilla.integratorproject.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.RecyclerView
import com.ludmilla.integratorproject.R
import com.ludmilla.integratorproject.data.factory.Constants
import com.ludmilla.integratorproject.data.remotesource.RemoteSource
import com.ludmilla.integratorproject.data.response.GenreResp
import com.ludmilla.integratorproject.data.response.ResponseGenre
import com.ludmilla.integratorproject.data.response.ResponseMovies
import com.ludmilla.integratorproject.presentation.adapter.GenreAdapter
import com.ludmilla.integratorproject.presentation.adapter.MoviesAdapter
import com.ludmilla.integratorproject.presentation.viewmodel.MovieViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PopularMovies : Fragment() {

    lateinit var moviesAdapter: MoviesAdapter
    lateinit var genreAdapter: GenreAdapter
    private lateinit var movieViewModel: MovieViewModel

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

        val rvmovies = view.findViewById<RecyclerView>(R.id.rvMovies)
        val rvgenre = view.findViewById<RecyclerView>(R.id.rvGenre)
        movieViewModel = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)
        moviesAdapter = MoviesAdapter(context = view.context)
        rvmovies.adapter = moviesAdapter
        genreAdapter = GenreAdapter(context = view.context)
        rvgenre.adapter = genreAdapter

 //       getPopularMovies()
 //       getGenre()
        initRequests()
        initObservers()
    }

    fun initRequests(){
        movieViewModel.getPopularMovies()
    }

    fun initObservers(){
        popularMovies()
    }

    private fun popularMovies() {
        movieViewModel.liveResponseMovie.observe(viewLifecycleOwner,{ movieList ->
            movieList?.let {
                moviesAdapter.listmovie.addAll(it)
                moviesAdapter.notifyDataSetChanged()
            }

        })
    }


/*    fun getPopularMovies() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL.value)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RemoteSource::class.java)
        val call = service.getPopularMovies(Constants.PUBLIC_KEY.value, "pt-br", 1)
        call.enqueue(object : Callback<ResponseMovies> {
            override fun onResponse(
                call: Call<ResponseMovies>,
                response: Response<ResponseMovies>,
            ) {
                if (response != null && response.code() == 200) {
                    val result = response.body()
                    response.body()?.results?.let {
                        moviesAdapter.listmovie.addAll(it)
                        moviesAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseMovies>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }*/

    /*  private fun displayGenre(
          response: Response<ResponseGenre>,
      ) {
              val resultgenre = response.body()
              response.body()?.genres.let {
                  if (it != null) {
                      genreAdapter.listgenre.addAll(it)
                  }
                  genreAdapter.notifyDataSetChanged()
              }
          }*/


}


/*fun getGenre(){
    val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL.value)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofit.create(RemoteSource::class.java)
    val call = service.getAllGenres(Constants.PUBLIC_KEY.value, "pt-br")
    call.enqueue(object : Callback<ResponseGenre> {
        override fun onResponse(
            call: Call<ResponseGenre>,
            response: Response<ResponseGenre>,
        ) {
            if (response != null && response.code() == 200) {
                val result = response.body()
                response.body()?.genres?.let {
                    genreAdapter.listgenre.addAll(it)
                    genreAdapter.notifyDataSetChanged()
                }
            }
        }

        override fun onFailure(call: Call<ResponseGenre>, t: Throwable) {
            TODO("Not yet implemented")
        }

    })
}*/
