package com.ludmilla.integratorproject

import com.google.gson.annotations.SerializedName

class CastResp (

        @SerializedName("name")
        val name: String? = null,
        @SerializedName("profile_path")
        val profile_path: String? = null,
        @SerializedName("character")
        val character: String? = null

)