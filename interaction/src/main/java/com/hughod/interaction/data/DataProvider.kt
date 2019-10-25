package com.hughod.interaction.data

interface DataProvider<T> {
    suspend fun fetch(): T
}
