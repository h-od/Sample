package com.hughod.interaction.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hughod.common.models.app.MovieListModel
import com.hughod.data.Repository
import com.hughod.interaction.Interactor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SetFilteredMovieListInteractor(
    override val job: Job = Job(),
    private val repository: Repository<MovieListModel>
) : Interactor<MovieListModel> {

    override val data: LiveData<MovieListModel>
        get() = _data

    override val error: LiveData<Boolean>
        get() = _error

    private val _data = MutableLiveData<MovieListModel>()
    private val _error = MutableLiveData<Boolean>()

    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)

    init {
        coroutineScope.launch { _data.postValue(repository.fetch()) }
    }
}
