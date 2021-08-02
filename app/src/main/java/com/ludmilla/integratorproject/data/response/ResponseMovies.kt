package com.ludmilla.integratorproject.data.response

import com.google.gson.annotations.SerializedName

data class ResponseMovies(
    @SerializedName("results")
    val results: List<ResponseMovie>
)