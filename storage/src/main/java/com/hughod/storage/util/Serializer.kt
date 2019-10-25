package com.hughod.storage.util

interface Serializer {
    fun <T> fromString(input: String?, clazz: Class<T>): T?
    fun <T> toString(input: Any?, clazz: Class<T>): String?
}
