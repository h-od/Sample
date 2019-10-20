package com.hughod.app.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.hughod.app.ui.util.JobCancelingViewModel
import com.hughod.common.models.app.MovieDetailModel
import com.hughod.interaction.detail.GetMovieInteractor

class DetailViewModel(
    interactor: GetMovieInteractor
) : JobCancelingViewModel(interactor) {
    val movie: LiveData<MovieDetailModel> = interactor.data
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(
    private val interactor: GetMovieInteractor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        DetailViewModel(interactor) as T
}

object DetailViewModelProvider {
    fun get(
        owner: ViewModelStoreOwner,
        viewModelProvider: ViewModelProvider.Factory
    ): DetailViewModel = ViewModelProvider(
        owner,
        viewModelProvider
    ).get(DetailViewModel::class.java)
}
