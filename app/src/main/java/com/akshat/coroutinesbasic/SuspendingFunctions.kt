package com.akshat.coroutinesbasic

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var functionCalls = 0

@OptIn(DelicateCoroutinesApi::class)
fun main(){
    GlobalScope.launch { completeMessage() }
    GlobalScope.launch { improveMessage() }
    print("Hello,  ")
    Thread.sleep(2000L)
    println("There have been $functionCalls calls so far")
}

suspend fun completeMessage(){
    delay(500L)
    println("World!")
    functionCalls++
}

suspend fun improveMessage(){
    delay(1000L)
    println("Suspend Functions are cool")
    functionCalls++
}