package com.akshat.coroutinesbasic.asynchronousFlow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.time.measureTime

fun main (){
    runBlocking {
        val time = measureTime {
            generate()
                .buffer()
                .collect{
                    delay(300L)
                    println(it)
                }
        }
        println("collected in $time ms")
    }

}

fun generate() = flow {
    for(i in 1..3){
        delay(100L)
        emit(i)
    }
}