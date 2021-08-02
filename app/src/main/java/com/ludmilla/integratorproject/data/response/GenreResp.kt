package com.ludmilla.integratorproject.data.response

import com.google.gson.annotations.SerializedName

class GenreResp (

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val genreName: String
)