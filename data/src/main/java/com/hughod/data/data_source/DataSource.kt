package com.hughod.data.data_source

interface ReadOnlyDataSource<T> {
    suspend fun get(id: Int = 0): T?
}

interface DataSource<T> : ReadOnlyDataSource<T> {
    fun add(item: T)
}
