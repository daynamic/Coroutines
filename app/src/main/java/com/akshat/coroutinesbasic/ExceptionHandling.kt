package com.akshat.coroutinesbasic

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    runBlocking {
        val myHandler = CoroutineExceptionHandler{coroutineContext, throwable ->
            println("Exception Handled: ${throwable.localizedMessage}")

        }
        val job = GlobalScope.launch(myHandler) {
            println("Throwing exception from job")
            throw IndexOutOfBoundsException("Exception in coroutine")
        }
        job.join()

        val deffered = GlobalScope.async {
            println("Throwing exception from async")
            throw ArithmeticException("exception from async")
        }
        try {
            deffered.await()
        } catch (e: ArithmeticException){
            println("exception ${e.message}")
        }

    }
}