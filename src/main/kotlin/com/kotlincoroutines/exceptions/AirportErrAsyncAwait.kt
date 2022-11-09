package com.kotlincoroutines.exceptions

import com.kotlincoroutines.base.AbstractAirportData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class AirportErrAsyncAwait : AbstractAirportData(
    airportCodes = listOf("LAX", "SF-", "PD-", "SEA")
) {
    override fun showInfo() : Unit = runBlocking {
        val airportsData = airportCodes.map { code ->
            async(Dispatchers.IO + SupervisorJob()) {
                 getAirportData(code)
            }
        }

        airportsData.forEach { airport ->
            try {
                val data = airport.await()
                data?.printInfo()
            }catch (ex : Exception){
                println("Airport data extraction failed with ${ex.message} ")
            }
        }
    }
}

fun main() {
    AirportErrAsyncAwait().showInfo()
}