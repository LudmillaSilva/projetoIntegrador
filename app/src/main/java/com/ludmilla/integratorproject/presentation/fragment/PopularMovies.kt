package com.ludmilla.integratorproject.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ludmilla.integratorproject.R
import com.ludmilla.integratorproject.data.factory.Constants
import com.ludmilla.integratorproject.data.remotesource.RemoteSource
import com.ludmilla.integratorproject.data.response.ResponseMovies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PopularMovies : Fragment() {


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

        getPopularMovies()
    }


    fun getPopularMovies() {
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
                    result?.results?.forEach { movie ->
                        movie.poster
                    }
                }
            }

            override fun onFailure(call: Call<ResponseMovies>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}