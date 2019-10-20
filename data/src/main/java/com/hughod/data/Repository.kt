package com.hughod.data

interface ReadOnlyRepository<T> {
    suspend fun fetch(): T
}

interface Repository<T>: ReadOnlyRepository<T> {
    suspend fun put(item: T)
}
