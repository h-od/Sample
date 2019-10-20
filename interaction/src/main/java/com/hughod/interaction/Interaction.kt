package com.hughod.interaction

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Job

interface Interactor<T>: JobProvider {
    val data: LiveData<T>
    val error: LiveData<Boolean>
}

interface JobProvider {
    val job: Job
}
