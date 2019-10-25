package com.hughod.interaction.interactor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hughod.interaction.Interactor
import com.hughod.interaction.data.DataProvider
import com.hughod.interaction.entities.MovieListEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GetAllMoviesInteractor(
    override val job: Job = Job(),
    private val repository: DataProvider<MovieListEntity>
) : Interactor<MovieListEntity, Boolean> {
    override val data: LiveData<MovieListEntity>
        get() = _data

    override val error: LiveData<Boolean>
        get() = _error

    private val _data = MutableLiveData<MovieListEntity>()
    private val _error = MutableLiveData<Boolean>()

    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)

    init {
        coroutineScope.launch {
            try {
                _error.postValue(false)
                _data.postValue(repository.fetch())
            } catch (e: Exception) {
                e.printStackTrace()
                _error.postValue(true)
            }
        }
    }
}
