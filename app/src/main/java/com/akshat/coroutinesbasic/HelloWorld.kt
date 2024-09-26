package com.akshat.coroutinesbasic

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
fun main(){
    GlobalScope.launch {
        delay(1000)
        println("World !")
    }

    print("Hello, ")
    Thread.sleep(2000)
}