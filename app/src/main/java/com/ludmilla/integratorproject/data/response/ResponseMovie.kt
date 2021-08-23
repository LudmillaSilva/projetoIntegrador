package com.ludmilla.integratorproject.data.response

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal


data class ResponseMovie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val poster: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("vote_average")
    val average: String? = null,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,

    var isFavorite: Boolean

){
    fun getAverageInPercent():String{
        return average!!.toBigDecimal().times(BigDecimal.valueOf(10)).toInt().toString()+"%"
    }
}