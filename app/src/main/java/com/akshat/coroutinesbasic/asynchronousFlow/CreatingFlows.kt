package com.akshat.coroutinesbasic.asynchronousFlow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main(){
    runBlocking {
        sendNumbers().collect{
            println("Received emit values  $it")
        }

        sendNumbersAsFlow().collect{
            println("Received As flow $it")
        }

        sendNumbersFlowOf().collect{
            println("Received Flow of $it")
        }
    }

}

fun sendNumbers() = flow{
    for (i in 1..10)
        emit(i)
}

fun sendNumbersAsFlow() = listOf(1,2,3,4).asFlow()



fun sendNumbersFlowOf() = flowOf(1,2,3,4)

