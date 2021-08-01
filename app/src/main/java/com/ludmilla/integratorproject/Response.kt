package com.ludmilla.integratorproject

import com.google.gson.annotations.SerializedName


data class Response(
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

)