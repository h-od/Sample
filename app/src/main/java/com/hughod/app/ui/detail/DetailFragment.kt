package com.hughod.app.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.hughod.app.R
import com.hughod.app.ui.ext.setClickListener
import com.hughod.app.ui.ext.setTextOrHide
import com.hughod.app.ui.ext.showImage
import com.hughod.app.ui.list.MovieModel
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

        view.setTextOrHide(R.id.title_tv, movie.title)
        view.setTextOrHide(R.id.body_tv, movie.body)

        DetailViewModelProvider.get(this, viewModelFactory).movie.observe(
            this,
            Observer { movieDetail ->
                view.showImage(R.id.backdrop_iv, movieDetail.imagePath)
                view.setTextOrHide(R.id.subtitle_tv, movieDetail.caption)
                view.setTextOrHide(R.id.title_tv, movieDetail.title)
                view.setTextOrHide(R.id.body_tv, movieDetail.description)
                view.setTextOrHide(R.id.extra_info_tv, movieDetail.extraInfo)
                view.setTextOrHide(R.id.genre_tv, movieDetail.genres)
                view.setTextOrHide(R.id.revenue_budget_tv, movieDetail.budgetAndRevenue)
                view.setTextOrHide(R.id.production_companies_tv, movieDetail.productionCompanies)

                view.setClickListener(R.id.home_page_tv) {
                    startActivity(movieDetail.homepage)
                }

                view.setClickListener(R.id.imdb_iv) {
                    startActivity(Intent().apply {
                        this.action = Intent.ACTION_VIEW
                        this.data = Uri.parse("https://www.imdb.com/title/${movieDetail.imdbId}")
                    })
                }
            })
    }
}
