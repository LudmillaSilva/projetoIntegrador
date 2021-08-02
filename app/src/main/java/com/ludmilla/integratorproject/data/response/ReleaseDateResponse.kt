package com.ludmilla.integratorproject.data.response

import com.google.gson.annotations.SerializedName

class ReleaseDateResponse (
    @SerializedName("certification")
    val certification: String,
    @SerializedName("type")
    val type: Int) {
}