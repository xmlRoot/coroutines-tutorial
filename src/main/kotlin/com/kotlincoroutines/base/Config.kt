package com.kotlincoroutines.base

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.http4k.client.JavaHttpClient
import org.http4k.core.Method.GET
import org.http4k.core.Request

val jsonMapper: ObjectMapper = jacksonObjectMapper()
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

val client by lazy { JavaHttpClient() }

inline fun <reified T> GET(url: String): T? =  jsonMapper.readValue(
    client(Request(GET, url).header("format", "application/json")).bodyString(),
    T::class.java
)
