package com.ludmilla.integratorproject

import com.google.gson.annotations.SerializedName

class ResponseCertification (

        @SerializedName("results")
        val results: List<CertificationResp>
)