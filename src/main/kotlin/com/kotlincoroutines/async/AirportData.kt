package com.kotlincoroutines.async

import com.kotlincoroutines.base.AbstractAirportData
import kotlin.system.measureTimeMillis

class AirportData : AbstractAirportData() {

    override fun showInfo() {
        printHeader()
        val time = measureTimeMillis {
            airportCodes
                .mapNotNull { code -> getAirportData(code) }
                .forEach { it.printInfo() }
        }
        println("Time taken $time ms")
    }

}

fun main() {
    AirportData().showInfo()
}