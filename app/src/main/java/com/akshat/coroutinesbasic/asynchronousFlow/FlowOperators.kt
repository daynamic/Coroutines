package com.akshat.coroutinesbasic.asynchronousFlow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        mapOperator()
        filterOperator()
        transformOperator()
        takeOperator()
        reduceOperator()
        flowOnOperator()
    }

}

//FlowOn Operator
suspend fun flowOnOperator(){
    (1..10).asFlow()
        .flowOn(Dispatchers.IO)
        .collect{
            println("Flow on $it")
        }
}


//reduce operator
suspend fun reduceOperator(){
    val size = 3
    val factorial = (1..size).asFlow()
        .reduce { accumulator, value ->
            accumulator * value
        }
    println("Factorial of $size is $factorial")
}

//Take flow
suspend fun takeOperator(){
    (1..10).asFlow()
        .take(2)
        .collect{
            println("take $it")
        }
}

//Map Operator
suspend fun mapOperator() {
    (1..10).asFlow()
        .map {
            delay(500L)
            "mapping $it"
        }
        .collect {
            println(it)
        }
}

//Filter Operator
suspend fun filterOperator() {
    (1..10).asFlow()
        .filter {
            it % 2 == 0
        }.collect {
            println("filter $it")
        }
}

//Transform Operator
suspend fun transformOperator() {
    (1..10).asFlow()
        .transform {
            emit("Emitting string value $it")
            emit(it)
        }
        .collect {
            println("transform $it")
        }
}