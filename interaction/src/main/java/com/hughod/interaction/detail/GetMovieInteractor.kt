package com.hughod.interaction.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hughod.common.models.app.MovieDetailModel
import com.hughod.data.ReadOnlyRepository
import com.hughod.interaction.Interactor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GetMovieInteractor(
    override val job: Job = Job(),
    private val repository: ReadOnlyRepository<MovieDetailModel>
) : Interactor<MovieDetailModel> {
    override val data: LiveData<MovieDetailModel>
        get() = _data

    override val error: LiveData<Boolean>
        get() = _error

    private val _data = MutableLiveData<MovieDetailModel>()
    private val _error = MutableLiveData<Boolean>()

    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)

    init {
        coroutineScope.launch {
            try {
                _data.postValue(repository.fetch())
                _error.postValue(false)
            } catch (e: Exception) {
                e.printStackTrace()
                _error.postValue(true)
            }
        }
    }
}
