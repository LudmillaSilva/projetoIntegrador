package com.ludmilla.integratorproject.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CastMovie(
    val name: String? = null,
    val profile_path: String? = null,
    val character: String? = null
): Parcelable

