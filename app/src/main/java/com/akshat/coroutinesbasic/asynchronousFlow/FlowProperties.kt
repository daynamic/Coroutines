package com.akshat.coroutinesbasic.asynchronousFlow

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeoutOrNull

fun main() {

    runBlocking {
        val numbersFlow = sendNewNumbers()
        println("Flow not started yet")
        println("starting flow now")
        numbersFlow.collect {
            println(it)
        }
    }

    runBlocking {
        val numbersFlow = sendNewNumbersCancellation()
        println("Flow not started yet")
        println("starting flow now")
        withTimeoutOrNull(1000L){
            numbersFlow.collect {
                println(it)
            }
        }
    }


}
//Cold Flow
fun sendNewNumbers() = flow {
    listOf(1, 2, 3).forEach { emit(it) }
}

//Cancellation
fun sendNewNumbersCancellation() = flow {
    listOf(1, 2, 3).forEach {
        delay(400L)
        emit(it)
    }
}