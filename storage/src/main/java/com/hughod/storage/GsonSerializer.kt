package com.hughod.storage

import com.google.gson.Gson
import com.hughod.storage.util.Serializer

class GsonSerializer(private val gson: Gson) : Serializer {

    override fun <T> fromString(input: String?, clazz: Class<T>): T? = gson.fromJson(input, clazz)

    override fun <T> toString(input: Any?, clazz: Class<T>): String? = if (input == null)
        null else gson.toJson(input, clazz)
}