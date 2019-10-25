package com.hughod.app.ui.detail

import androidx.lifecycle.*
import com.hughod.app.ui.util.JobCancelingViewModel
import com.hughod.interaction.Interactor
import com.hughod.interaction.entities.DetailEntity
import com.hughod.interaction.interactor.GetMovieInteractor

class DetailViewModel(
    interactor: Interactor<DetailEntity, Boolean>
) : JobCancelingViewModel(interactor) {
    val movie: LiveData<DetailModel> = Transformations.map(interactor.data) { DetailModel.fromDomain(it) }
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
