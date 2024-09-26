package com.akshat.coroutinesbasic

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun main(){
    runBlocking {
        /*launch(Dispatchers.Main) {
            println("Main dispatcher. Thread: ${Thread.currentThread().name}")
        }*/

        launch(Dispatchers.Unconfined) {
            println("Unconfined1 dispatcher. Thread: ${Thread.currentThread().name}")
            delay(100L)
            println("Unconfined2 dispatcher. Thread: ${Thread.currentThread().name}")
        }

        launch(Dispatchers.Default) {
            println("Default dispatcher. Thread: ${Thread.currentThread().name}")
        }

        launch(Dispatchers.IO) {
            println("IO dispatcher. Thread: ${Thread.currentThread().name}")
        }

        launch(newSingleThreadContext("Mythread")){
            println("newSingleThreadContext. Thread: ${Thread.currentThread().name}")
        }
    }
}