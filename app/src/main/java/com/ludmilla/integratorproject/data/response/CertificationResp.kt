package com.ludmilla.integratorproject.data.response

import com.google.gson.annotations.SerializedName

class CertificationResp (
    @SerializedName("iso_3166_1")
    val region: String,
    @SerializedName("release_dates")
    val release_date: List<ReleaseDateResponse>
)