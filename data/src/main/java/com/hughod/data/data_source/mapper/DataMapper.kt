package com.hughod.data.data_source.mapper

interface DataMapper<From, To> {
    fun map(data: From?): To?
}
