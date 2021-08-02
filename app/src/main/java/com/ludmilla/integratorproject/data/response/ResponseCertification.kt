package com.ludmilla.integratorproject.data.response

import com.google.gson.annotations.SerializedName
import com.ludmilla.integratorproject.data.response.CertificationResp

class ResponseCertification (

        @SerializedName("results")
        val results: List<CertificationResp>
)