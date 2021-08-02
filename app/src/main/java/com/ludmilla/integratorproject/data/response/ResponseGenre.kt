package com.ludmilla.integratorproject.data.response

import com.google.gson.annotations.SerializedName

data class ResponseGenre (
    @SerializedName("genres")
    val genres: List<GenreResp>
)

