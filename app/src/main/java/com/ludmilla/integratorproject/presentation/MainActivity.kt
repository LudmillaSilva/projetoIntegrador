package com.ludmilla.integratorproject.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import android.widget.SearchView
import androidx.core.net.toUri
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ludmilla.integratorproject.data.factory.Constants
import com.ludmilla.integratorproject.data.remotesource.RemoteSource
import com.ludmilla.integratorproject.data.response.ResponseMovies
import com.ludmilla.integratorproject.R
import com.ludmilla.integratorproject.presentation.adapter.adapter
import com.ludmilla.integratorproject.presentation.fragment.PopularMovies
import com.ludmilla.integratorproject.presentation.viewmodel.FavoritesViewModel
import com.ludmilla.integratorproject.presentation.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewpager: ViewPager2
    lateinit var tablayout: TabLayout
    lateinit var searchMovie: SearchView
    private var popularmovies: PopularMovies? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieViewModel: MovieViewModel by viewModel()
        val favoriteViewModel: FavoritesViewModel by viewModel()

        viewpager= findViewById(R.id.viewPager)
        tablayout= findViewById(R.id.tabLayout)
        searchMovie= findViewById(R.id.searchMovie)
//        val movieSearch = searchMovie.text.toString()

        viewpager.adapter = adapter(this)
        viewpager.isUserInputEnabled = false
        TabLayoutMediator(tablayout, viewpager){ tab, position ->
            tab.text = tabTitle(position)
        }.attach()
        addActions()

    }


    private fun addActions(){
        searchMovie.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchMovie.clearFocus()
                query?.let{
                    createPopularMoviesInstance(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText?.isNotBlank()== true){
                    Handler(Looper.getMainLooper()).postDelayed({
                        newText.let{
                            createPopularMoviesInstance(it)
                        }
                    }, AUTO_SEARCH_TIME)
                }
                return true
            }

        })
    }

    private fun createPopularMoviesInstance(query: String){
        if(popularmovies == null){
            popularmovies = PopularMovies.newInstance(query)
            popularmovies?.let{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.rvMoviesHome,it)
                    .commit()
            }
        }else{
            popularmovies?.searchMovie(query.toUri())
        }
    }

    companion object{
        private const val AUTO_SEARCH_TIME = 1500L
    }



    private fun tabTitle(position: Int): String{
        return when (position){
            0 -> "Todos os filmes"
            1 -> "Favoritos"
            else -> ""
        }
    }
}