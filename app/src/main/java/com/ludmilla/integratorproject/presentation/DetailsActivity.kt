package com.ludmilla.integratorproject.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView
import com.ludmilla.integratorproject.R
import com.ludmilla.integratorproject.presentation.viewmodel.MovieViewModel

class DetailsActivity : AppCompatActivity() {

    private lateinit var returnbtn: FloatingActionButton
    private lateinit var postermovie: ShapeableImageView
    private lateinit var rationmovieinfoact: TextView
    private lateinit var movietitle: TextView
    private lateinit var movieyear: TextView
    private lateinit var txtrestriction: TextView
    private lateinit var movieduration: TextView
    private lateinit var favbutton: ToggleButton
    private lateinit var genresrvinfo: RecyclerView
    private lateinit var txtviewmoviesynopsis: TextView
    private lateinit var castrv: RecyclerView
/*
    private lateinit var castRvAdapter: CastRvAdapter
    private lateinit var genresRvAdapter: MovieDetailsGenresRvAdapter
*/
    private val viewModel = MovieViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_synopsis)

        returnbtn = findViewById(R.id.fltReturnBtn)
        postermovie = findViewById(R.id.imgPosterMovie)
        rationmovieinfoact = findViewById(R.id.ratingMovieInfoAct)
        movietitle = findViewById(R.id.movieTitle)
        movieyear = findViewById(R.id.txtMovieYear)
        txtrestriction = findViewById(R.id.txtRestriction)
        movieduration = findViewById(R.id.txtMovieDuration)
        favbutton = findViewById(R.id.favBtnMovieInfo)
        genresrvinfo = findViewById(R.id.genreRvInfo)
        txtviewmoviesynopsis = findViewById(R.id.movieSynopsis)
        castrv = findViewById(R.id.castRv)

        val movieId = intent.extras?.getInt("MOVIE_ID")

        viewModel.getDetailMovie(movieId!!)


        returnbtn.setOnClickListener{
            finish()
        }

       /* private fun getMovieDetail(){
            viewModel.movie
        }*/

    }


}