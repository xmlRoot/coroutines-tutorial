package com.kotlincoroutines.exceptions

import com.kotlincoroutines.base.AbstractAirportData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AirportDataErr : AbstractAirportData(
    airportCodes = listOf("LAX", "SF-", "PD-", "SEA")
) {
    override fun showInfo() : Unit = runBlocking {
        try {

            val handler = CoroutineExceptionHandler { context, ex ->
                println("${context[CoroutineName]} failed with ${ex.message}")
            }

            val jobs : List<Job> = airportCodes.map { code ->
                launch (Dispatchers.IO + CoroutineName(code) + SupervisorJob()
//                        + handler
                ) {
                    val airport = getAirportData(code)
                    println("$code delay: ${airport?.delay}")
                }
            }
            jobs.forEach { it.join() }
            jobs.forEach { println("job is cancelled = ${it.isCancelled}") }
        }catch (ex : Exception) {
            println("Error : ${ex.message}")
        }
    }
}

fun main() {
    AirportDataErr().showInfo()
}