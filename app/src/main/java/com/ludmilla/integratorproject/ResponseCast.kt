package com.ludmilla.integratorproject

import com.google.gson.annotations.SerializedName

class ResponseCast (
    @SerializedName("cast")
    val cast: List<CastResp>
)