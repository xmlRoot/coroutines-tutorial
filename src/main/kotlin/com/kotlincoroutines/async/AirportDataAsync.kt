package com.kotlincoroutines.async

import com.kotlincoroutines.base.Airport
import com.kotlincoroutines.base.AbstractAirportData
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

class AirportDataAsync : AbstractAirportData(){

    override fun showInfo() = runBlocking {
        printHeader()
        val time = measureTimeMillis {
            val airportData: List<Deferred<Airport?>> =
                airportCodes.map { code ->
                    async(Dispatchers.IO) {
                        getAirportData(code)
                    }
                }
            airportData
                .mapNotNull { data -> data.await() }
                .forEach { airport -> airport.printInfo() }
        }

        println("Time taken $time ms")
    }
}

fun main() {
    AirportDataAsync().showInfo()
}