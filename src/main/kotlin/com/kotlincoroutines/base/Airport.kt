package com.kotlincoroutines.base

import com.fasterxml.jackson.annotation.JsonProperty

data class Weather(
  @get:JsonProperty("Temp")
  val temperature: List<String>
)

data class Airport(
    @get:JsonProperty("IATA")
    val code: String? = null,
    @get:JsonProperty("Name")
    val name: String,
    @get:JsonProperty("Delay")
    val delay: Boolean,
    @get:JsonProperty("com.kotlincoroutines.base.Weather")
    val weather: Weather?
)