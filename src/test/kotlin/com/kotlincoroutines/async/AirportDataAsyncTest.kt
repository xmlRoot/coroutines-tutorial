package com.kotlincoroutines.async

import org.junit.jupiter.api.Test

class AirportDataAsyncTest {

    private val airportData = AirportDataAsync()

    @Test
    fun testShowAirportData(){
        airportData.showInfo()
    }

}