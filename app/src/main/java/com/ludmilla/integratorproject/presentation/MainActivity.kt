package com.ludmilla.integratorproject.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.ludmilla.integratorproject.data.factory.Constants
import com.ludmilla.integratorproject.data.remotesource.RemoteSource
import com.ludmilla.integratorproject.data.response.ResponseMovies
import com.ludmilla.integratorproject.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getPopularMovies()
    }
    fun getPopularMovies(){
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL.value)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RemoteSource::class.java)
        val call = service.getPopularMovies(Constants.PUBLIC_KEY.value,"pt-br",1)
        call.enqueue(object :Callback<ResponseMovies>{
            override fun onResponse(
                call: Call<ResponseMovies>,
                response: Response<ResponseMovies>,
            ) {
                if(response!=null && response.code()==200){
                    val result = response.body()
                    result?.results?.forEach { movie->
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