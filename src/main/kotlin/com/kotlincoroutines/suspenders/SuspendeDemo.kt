package com.kotlincoroutines.suspenders

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import java.lang.Thread.currentThread

suspend fun callTask( name : String = "1", delayTime : Long = 1000)  {
    println("Started task $name from ${currentThread()}")
//    yield()
//    delay(delayTime)
    println("Completed task $name from ${currentThread()}")
}

fun main() = runBlocking {
    launch(Dispatchers.Default) { callTask("1") }
    launch { callTask("2", delayTime = 500) }

    println("Called two tasks from ${currentThread()}")
}
