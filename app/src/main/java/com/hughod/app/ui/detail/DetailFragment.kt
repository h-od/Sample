package com.hughod.app.ui.detail

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.hughod.app.R
import com.hughod.app.ui.ext.findAndSetTextOrHide
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

        view.findAndSetTextOrHide(R.id.title_tv, movie.title)
        view.findAndSetTextOrHide(R.id.body_tv, movie.body)

        DetailViewModelProvider.get(
            this,
            viewModelFactory
        ).movie.observe(this, Observer { movieDetail ->
            view.showImage(R.id.backdrop_iv, movieDetail.imagePath)
            view.findAndSetTextOrHide(R.id.subtitle_tv, movieDetail.caption)
            view.findAndSetTextOrHide(R.id.title_tv, movieDetail.title)
            view.findAndSetTextOrHide(R.id.body_tv, movieDetail.description)
            view.findAndSetTextOrHide(R.id.extra_info_tv, movieDetail.extraInfo)
            view.findAndSetTextOrHide(R.id.genre_tv, movieDetail.genres)
            view.findAndSetTextOrHide(R.id.revenue_budget_tv, movieDetail.budgetAndRevenue)
            view.findAndSetTextOrHide(R.id.production_companies_tv, movieDetail.productionCompanies)

            view.showHomePage(movieDetail)
            view.showImdbLink(movieDetail)
        })
    }

    private fun View.showHomePage(model: DetailModel) {
        val homePageView = this.findViewById<View>(R.id.home_page_tv)

        homePageView.visibility = if (model.hasHomePage()) {
            homePageView.setOnClickListener {
                startActivity(model.homepage)
            }
            VISIBLE
        } else GONE
    }

    private fun View.showImdbLink(model: DetailModel) {
        val imdbView = this.findViewById<View>(R.id.imdb_iv)

        imdbView.visibility = if (model.hasImdb()) {
            imdbView.setOnClickListener {
                startActivity(model.imdbId)
            }
            VISIBLE
        } else GONE
    }
}
