package com.hughod.app.ui.util

import androidx.lifecycle.ViewModel
import com.hughod.interaction.JobProvider

abstract class JobCancelingViewModel(private val jobProviders: List<JobProvider>) : ViewModel() {

    constructor(jobProvider: JobProvider) : this(listOf(jobProvider))

    override fun onCleared() {
        jobProviders.forEach { it.job.cancel() }
        super.onCleared()
    }
}
