package com.hughod.app.ui.list

import androidx.lifecycle.*
import com.hughod.app.ui.util.JobCancelingViewModel
import com.hughod.interaction.interactor.GetAllMoviesInteractor

class ListViewModel(
    interactor: GetAllMoviesInteractor
) : JobCancelingViewModel(interactor) {
    val movies: LiveData<ListModel> = Transformations.map(interactor.data) { MovieModel.fromDomain(it) }
    val error: LiveData<Boolean> = interactor.error
}

@Suppress("UNCHECKED_CAST")
class ListViewModelFactory(
    private val interactor: GetAllMoviesInteractor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ListViewModel(interactor) as T
}

object ListViewModelProvider {
    fun get(
        owner: ViewModelStoreOwner,
        viewModelProvider: ViewModelProvider.Factory
    ): ListViewModel = ViewModelProvider(owner, viewModelProvider).get(ListViewModel::class.java)
}
