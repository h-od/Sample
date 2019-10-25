package com.hughod.interaction.data

interface Mapper<in T, out U> {
    fun map(from: T?): U?
}
