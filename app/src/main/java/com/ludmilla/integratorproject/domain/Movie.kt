package com.ludmilla.integratorproject.domain

import android.os.Parcelable
import com.ludmilla.integratorproject.data.response.ResponseMovie
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val img: String? = null,
    val title: String? = null,
    val rating: String? = null,
    val genreId: List<Int>,
    var isFavorite: Boolean = false,
) : Parcelable {
    companion object {
        fun parserToMovie(responseMovie: ResponseMovie?): Movie? {
            responseMovie?.let {
                return Movie(it.id, it.poster, it.title, it.average, it.genreIds, it.isFavorite)
            }
            return null;
        }
    }
}
