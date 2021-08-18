package com.ludmilla.integratorproject.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.ludmilla.integratorproject.R
import com.ludmilla.integratorproject.data.response.GenreResp
import com.ludmilla.integratorproject.domain.Genre

class GenreDetailsAdapter (private var listgenre: List<GenreResp> = mutableListOf()): RecyclerView.Adapter<GenreDetailsAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val itemGenre: Chip? = view.findViewById(R.id.itemGenre)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemGenre?.text = listgenre[position].genreName
    }

    override fun getItemCount() = listgenre.size
}