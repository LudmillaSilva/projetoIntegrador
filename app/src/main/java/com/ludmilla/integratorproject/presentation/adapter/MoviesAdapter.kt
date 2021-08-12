package com.ludmilla.integratorproject.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ludmilla.integratorproject.R
import com.ludmilla.integratorproject.data.factory.Constants
import com.ludmilla.integratorproject.data.response.ResponseMovie
import com.ludmilla.integratorproject.domain.Movie
import com.ludmilla.integratorproject.presentation.adapter.MoviesAdapter.*
import com.ludmilla.integratorproject.presentation.fragment.ListenerMovies

class MoviesAdapter (
    val context: Context,
    var listmovie: MutableList<ResponseMovie> = mutableListOf(),
    val listener: ListenerMovies? = null
): RecyclerView.Adapter<ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageMovie: ImageView? = view.findViewById(R.id.imgMovie)
        var titleMovie: TextView? = view.findViewById(R.id.txtTitle)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_movie, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(listmovie[position].poster !== ""){
            holder.imageMovie?.let { Glide.with(context).load(Constants.BASE_URL_IMAGE.value + listmovie[position].poster).into(it) }
        }
        holder.titleMovie?.text = listmovie[position].title
       // holder.rateMovie?.text = dataset[position].rating

        /*holder.imgMovie?.setOnClickListener {
            listener?.openMovieDetails(dataset[position].id)
        }

        holder.favBtn?.isChecked = dataset[position].isFavorite
        holder.favBtn?.setOnClickListener {
            listener?.onFavoriteClickedListener(dataset[position], !dataset[position].isFavorite)
        }*/

    }

    override fun getItemCount() = listmovie.size

}

