package com.ludmilla.integratorproject.presentation.adapter

import android.content.Context
import android.graphics.Movie
import androidx.recyclerview.widget.RecyclerView

class MoviesAdapter (
    val context: Context,
    var listmovie: MutableList<Movie> = mutableListOf()
): RecyclerView.Adapter<MoviesAdapter.ViewHolder>()


