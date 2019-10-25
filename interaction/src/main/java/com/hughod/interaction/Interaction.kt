package com.hughod.interaction

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Job

interface Interactor<T, U>: JobProvider {
    val data: LiveData<T>
    val error: LiveData<U>
}

interface JobProvider {
    val job: Job
}
