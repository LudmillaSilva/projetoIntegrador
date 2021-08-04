package com.ludmilla.integratorproject.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val img: String? = null,
    val id: Int,
    val title: String? = null,
    val rating: String? = null,
    val genreId: List<Int>,
    var isFavorite: Boolean = false,
) : Parcelable
