package com.hughod.app.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.hughod.app.BuildConfig
import com.hughod.app.R
import com.hughod.app.ui.util.setTextOrHide
import com.hughod.common.extensions.decimalFormat
import com.hughod.common.models.app.MovieModel
import org.koin.androidx.scope.currentScope
import org.koin.core.parameter.parametersOf

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val movie: MovieModel by lazy {
        MovieModel.fromBundle(arguments)
            ?: throw IllegalArgumentException("Args should contain a movie")
    }

    private val viewModelFactory: DetailViewModelFactory by currentScope.inject { parametersOf(movie.id) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView = view.findViewById<ImageView>(R.id.backdrop_iv)

        showImage(imageView, movie.backdropPath)

        view.setTextOrHide(R.id.title_tv, movie.name)
        view.setTextOrHide(R.id.overview_tv, movie.overview)
        view.setTextOrHide(R.id.release_date_tv, movie.releaseDate)
        view.setTextOrHide(R.id.rating_tv, movie.rating.toString())

        DetailViewModelProvider.get(this, viewModelFactory).movie.observe(
            this,
            Observer { movieDetail ->
                showImage(imageView, movieDetail.backdropPath)

                view.setTextOrHide(R.id.tagline_tv, movieDetail.tagline)
                view.setTextOrHide(R.id.title_tv, movieDetail.title)
                view.setTextOrHide(R.id.overview_tv, movieDetail.overview)
                view.setTextOrHide(R.id.release_date_tv, movieDetail.releaseDate)
                view.setTextOrHide(R.id.run_time_tv, "${movieDetail.runtime} mins")
                view.setTextOrHide(R.id.rating_tv, movieDetail.popularity.toString())
                view.setTextOrHide(R.id.genre_tv, movieDetail.genres.joinToString())
                view.setTextOrHide(
                    R.id.revenue_tv,
                    "Revenue: £${movieDetail.revenue.decimalFormat()}"
                )
                view.setTextOrHide(R.id.budget_tv, "Budget: £${movieDetail.budget.decimalFormat()}")
                view.setTextOrHide(
                    R.id.production_companies_tv,
                    movieDetail.productionCompanies.joinToString()
                )
                view.setTextOrHide(R.id.home_page_tv, movieDetail.homepage)

                view.findViewById<TextView>(R.id.home_page_tv).setOnClickListener {
                    startActivity(Intent().apply {
                        this.action = Intent.ACTION_VIEW
                        this.data = Uri.parse(movieDetail.homepage)
                    })
                }

                view.findViewById<ImageView>(R.id.imdb_iv).setOnClickListener {
                    startActivity(Intent().apply {
                        this.action = Intent.ACTION_VIEW
                        this.data = Uri.parse("https://www.imdb.com/title/${movieDetail.imdbId}")
                    })
                }
            })
    }

    private fun showImage(view: ImageView, imagePath: String?) {

        if (imagePath.isNullOrBlank()) {

        } else {
            val imageSize = "w500"
            Glide.with(this).load("https://${BuildConfig.IMAGE_URL}$imageSize$imagePath")
                .into(view)
        }
    }
}
