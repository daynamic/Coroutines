package com.akshat.coroutinesbasic

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {

    runBlocking {
        repeat(100_000) {
            launch {
                print(".")
            }
        }
    }

}