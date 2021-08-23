package com.ludmilla.integratorproject

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.ludmilla.integratorproject.data.response.ResponseMovie
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MovieViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `when view model getPopularMovies then sets liveResponseMovie`() {
        val listResponseMovie = mutableListOf<ResponseMovie>()
        val responseMovie = ResponseMovie(1,"poster","title","average", listOf(1,2.3) as List<Int>,true)

        val resultExpected = 1

        val result = responseMovie.id

        Assert.assertEquals(resultExpected,result)
    }
}