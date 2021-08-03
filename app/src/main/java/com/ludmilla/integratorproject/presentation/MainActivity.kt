package com.ludmilla.integratorproject.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ludmilla.integratorproject.data.factory.Constants
import com.ludmilla.integratorproject.data.remotesource.RemoteSource
import com.ludmilla.integratorproject.data.response.ResponseMovies
import com.ludmilla.integratorproject.R
import com.ludmilla.integratorproject.presentation.adapter.adapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewpager: ViewPager2
    lateinit var tablayout: TabLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewpager= findViewById(R.id.viewPager)
        tablayout= findViewById(R.id.tabLayout)

        viewpager.adapter = adapter(this)
        TabLayoutMediator(tablayout, viewpager){ tab, position ->
            tab.text = tabTitle(position)
        }.attach()

    }

    private fun tabTitle(position: Int): String{
        return when (position){
            0 -> "Todos os filmes"
            1 -> "Favoritos"
            else -> ""
        }
    }
}