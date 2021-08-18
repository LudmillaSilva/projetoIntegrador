package com.ludmilla.integratorproject.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_favorites")
data class Favorite (
    @PrimaryKey
    val id: Long = 0L,

    @ColumnInfo(name = "img")
    val img: String? = null,

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "average")
    val average: String? = null,

    @ColumnInfo(name = "genreIds")
    val genreIds: String? = null
)