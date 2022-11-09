package com.kotlincoroutines.base

abstract class AbstractAirportData(
    private val template: String = "%-10s%-20s%-10s",
    protected val airportCodes: List<String> = listOf("LAX", "SFO", "PDX", "SEA")
) {
    companion object {
        const val BASE_URL = "https://soa.smext.faa.gov/asws/api/airport/status"
    }

    protected fun Airport.printInfo() {
        println(template.format(code, weather?.temperature?.get(0), delay))
    }

    protected fun printHeader() = println(template.format("Code", "Temperature", "Delay"))

    protected fun getAirportData(code: String) = GET<Airport>("$BASE_URL/$code")

    abstract fun showInfo()
}