package com.ludmilla.integratorproject.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.ludmilla.integratorproject.R
import com.ludmilla.integratorproject.data.response.GenreResp
import com.ludmilla.integratorproject.data.response.ResponseGenre
import com.ludmilla.integratorproject.domain.Genre

class GenreAdapter (
    val context: Context,
    var listgenre: MutableList<GenreResp> = mutableListOf()
    ): RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    private val items: MutableList<Int> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val itemgenre: Chip? = view.findViewById(R.id.itemGenre)

        }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_genre, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreAdapter.ViewHolder, position: Int) {
        holder.itemgenre?.text = listgenre[position].genreName
        holder.itemgenre?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                items.add(listgenre[position].id)
            } else {
                items.remove(listgenre[position].id)
            }
        }
    }
    override fun getItemCount() = listgenre.size
 }


