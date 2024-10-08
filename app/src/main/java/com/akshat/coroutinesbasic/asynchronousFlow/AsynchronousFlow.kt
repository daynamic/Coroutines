package com.akshat.coroutinesbasic.asynchronousFlow

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.Flow


fun main() {

    runBlocking {
        println("Receiving Prime numbers")
        sendPrime().collect {
            println("Received Prime number $it")
        }
        println("Finished Receiving number")
    }

}

fun sendPrime(): Flow<Int> = flow {
    val primeList = listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)
    primeList.forEach {
        delay(it * 100L)
        emit(it)
    }

}