package com.akshat.coroutinesbasic

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main(){
    runBlocking {
        launch(Dispatchers.Default) {
            println("First Context: $coroutineContext")
            withContext(Dispatchers.IO){
                println("Second context: $coroutineContext")
            }
            println("Third context: $coroutineContext")
        }
    }
}