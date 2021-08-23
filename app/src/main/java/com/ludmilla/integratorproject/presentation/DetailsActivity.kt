package com.ludmilla.integratorproject.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView
import com.ludmilla.integratorproject.R
import com.ludmilla.integratorproject.data.factory.Constants
import com.ludmilla.integratorproject.data.model.Favorite
import com.ludmilla.integratorproject.data.response.CastResp
import com.ludmilla.integratorproject.data.response.ResponseDetail
import com.ludmilla.integratorproject.domain.Movie
import com.ludmilla.integratorproject.presentation.adapter.CastAdapter
import com.ludmilla.integratorproject.presentation.adapter.GenreDetailsAdapter
import com.ludmilla.integratorproject.presentation.viewmodel.FavoritesViewModel
import com.ludmilla.integratorproject.presentation.viewmodel.MovieViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

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
    private lateinit var castAdapter: CastAdapter
    private lateinit var genresDetailsAdapter: GenreDetailsAdapter
    private val movieViewModel : MovieViewModel by viewModel()
    private val favoriteViewModel: FavoritesViewModel by viewModel()


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

        val movie: Movie? = intent.extras?.getParcelable("MOVIE_ID")

        movie?.let{
            initCheckFavorite(it.id)
            handleFavorite(it)
            movieViewModel.getDetailMovie(it.id)
            movieViewModel.getCast(it.id)
        }


        checkFavoriteObserver()
        detailMovieObserver()
        castObserver()


        returnbtn.setOnClickListener{
            finish()
        }


    }

    fun handleFavorite(movie:Movie){
        favbutton.setOnClickListener {
            val favorite = Favorite(
                movie.id.toLong(),
                movie.img,
                movie.title,
                movie.rating,
                movie.genreId.joinToString (","))
            if (favbutton.isChecked){
                favoriteViewModel.saveFavorite(favorite)
            }else{
                favoriteViewModel.deleteFavorite(favorite)
            }
        }
    }

    @DelicateCoroutinesApi
    private fun initCheckFavorite(id:Int){
        GlobalScope.launch {
            favoriteViewModel.checkIfIsFavorite(id)
        }
    }

    private fun checkFavoriteObserver(){
        favoriteViewModel.liveCheckFavorite.observe(this,{ isFavorite ->
            isFavorite?.let {
                favbutton.isChecked = it
            }
        })
    }

    private fun castObserver(){
        movieViewModel.liveResponseCast.observe(this, { cast ->
            cast?.let{
                showCast(it)
                castAdapter.listCast.addAll(it)
                castAdapter.notifyDataSetChanged()
            }
        })

    }

    private fun showCast(cast: List<CastResp>) {
        castAdapter = CastAdapter(this, cast.toMutableList())
        castrv.adapter = castAdapter

    }

    private fun detailMovieObserver(){
        movieViewModel.liveResponseDetailMovie.observe(this,{ movieDetails ->
            movieDetails?.let{
                showMovieDetails(it)
            }
        })
    }

    private fun showMovieDetails(movie: ResponseDetail) {
        genresDetailsAdapter = GenreDetailsAdapter(movie.genres)
        genresrvinfo.adapter = genresDetailsAdapter
        txtviewmoviesynopsis.text = movie.overview
        movietitle.text = movie.title
        Glide.with(this).load(Constants.BASE_URL_IMAGE.value + movie.backdrop_path ).into(postermovie)
        rationmovieinfoact.text = movie.vote_average.toString()
        movieyear.text = movie.release_date.take(4)
  //      txtrestriction.text = TODO Buscar informação da API
 //       movieduration.text = TODO Montar informação de outra forma

    }


}