package com.ludmilla.integratorproject

import com.google.gson.annotations.SerializedName

data class ResponseMovies(
    @SerializedName("results")
    val results: List<Response>
)