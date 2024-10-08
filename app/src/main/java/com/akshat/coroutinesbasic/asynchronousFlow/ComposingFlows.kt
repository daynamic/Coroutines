package com.akshat.coroutinesbasic.asynchronousFlow

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

fun main(){
runBlocking { zip() }
}

suspend fun zip(){
    val english = flowOf("one","two", "three")
    val random = flowOf("hhh", "hhdh", "hfjdjj")
    english.zip(random){a,b -> "'$a' in random is '$b'" }
        .collect{
            println(it)
        }
}