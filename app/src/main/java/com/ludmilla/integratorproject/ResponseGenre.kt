package com.ludmilla.integratorproject

import com.google.gson.annotations.SerializedName

data class ResponseGenre (
    @SerializedName("genres")
    val genres: List<GenreResp>
)

