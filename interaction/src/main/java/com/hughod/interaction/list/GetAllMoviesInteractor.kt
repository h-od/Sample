package com.hughod.interaction.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hughod.common.models.app.MovieListModel
import com.hughod.data.ReadOnlyRepository
import com.hughod.interaction.Interactor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GetAllMoviesInteractor(
    override val job: Job = Job(),
    private val repository: ReadOnlyRepository<MovieListModel>
) : Interactor<MovieListModel> {
    override val data: LiveData<MovieListModel>
        get() = _data

    override val error: LiveData<Boolean>
        get() = _error

    private val _data = MutableLiveData<MovieListModel>()
    private val _error = MutableLiveData<Boolean>()

    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)

    init {
        coroutineScope.launch {
            try {
                //todo retry
                val data = repository.fetch()
                _error.postValue(data.isNullOrEmpty())
                _data.postValue(data)
            } catch (e: Exception) {
                e.printStackTrace()
                _error.postValue(true)
            }
        }
    }
}
