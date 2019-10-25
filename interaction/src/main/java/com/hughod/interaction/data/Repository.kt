package com.hughod.interaction.data

interface Repository<T> : DataProvider<T> {
    suspend fun put(item: T)
}
