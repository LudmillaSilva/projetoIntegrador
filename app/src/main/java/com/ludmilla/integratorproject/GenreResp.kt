package com.ludmilla.integratorproject

import com.google.gson.annotations.SerializedName

class GenreResp (

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val genreName: String
)