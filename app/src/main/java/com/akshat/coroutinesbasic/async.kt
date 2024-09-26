package com.akshat.coroutinesbasic

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main(){
    runBlocking {
        val firstDeffered = async { getFirstValue() }
        val secondDeffered = async { getSecondValue() }

        println("Doing some processing")
        delay(500L)
        println("Wating for values")
        
        val firstValue = firstDeffered.await()
        val secondValue = secondDeffered.await()

        println("The total is $firstValue + $secondValue")

    }
}

suspend fun getFirstValue(): Int{
    delay(1000L)
    val value = Random.nextInt(100)
    println("Returning first value $value")
    return value
}

suspend fun getSecondValue(): Int{
    delay(1000L)
    val value = Random.nextInt(100)
    println("Returning Second value $value")
    return value
}