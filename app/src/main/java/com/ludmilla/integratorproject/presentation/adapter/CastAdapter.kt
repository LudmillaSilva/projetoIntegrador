package com.ludmilla.integratorproject.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ludmilla.integratorproject.R
import com.ludmilla.integratorproject.data.factory.Constants
import com.ludmilla.integratorproject.data.response.CastResp
import com.ludmilla.integratorproject.data.response.ResponseDetail
import com.ludmilla.integratorproject.domain.CastMovie
import com.ludmilla.integratorproject.presentation.fragment.ListenerMovies

class CastAdapter (val context: Context,
   val listCast: MutableList<CastResp> = mutableListOf())

    : RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val personImg = view.findViewById<ImageView>(R.id.personImg)
        val personName = view.findViewById<TextView>(R.id.personName)
        val character = view.findViewById<TextView>(R.id.personRole)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.personImg?.let{ Glide.with(context).load(Constants.BASE_URL_IMAGE.value + listCast[position].profile_path).into(it) }
        holder.personName?.text = listCast[position].name
        holder.character?.text = listCast[position].character
    }

    override fun getItemCount() = listCast.size
}