package com.ludmilla.integratorproject.data.response

import com.google.gson.annotations.SerializedName
import com.ludmilla.integratorproject.data.response.CastResp

class ResponseCast (
    @SerializedName("cast")
    val cast: List<CastResp>
)