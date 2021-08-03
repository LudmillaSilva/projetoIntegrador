package com.ludmilla.integratorproject.presentation.adapter

import android.icu.text.Transliterator
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ludmilla.integratorproject.presentation.fragment.FavoriteMovies
import com.ludmilla.integratorproject.presentation.fragment.PopularMovies

class adapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> PopularMovies()
            1 -> FavoriteMovies()
        else -> PopularMovies()

        }

    }

}