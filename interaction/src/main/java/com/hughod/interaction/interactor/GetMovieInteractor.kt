package com.hughod.interaction.interactor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hughod.interaction.Interactor
import com.hughod.interaction.data.DataProvider
import com.hughod.interaction.entities.DetailEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GetMovieInteractor(
    override val job: Job = Job(),
    private val repository: DataProvider<DetailEntity>
) : Interactor<DetailEntity, Boolean> {
    override val data: LiveData<DetailEntity>
        get() = _data

    override val error: LiveData<Boolean>
        get() = _error

    private val _data = MutableLiveData<DetailEntity>()
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
