package com.example.randomnumber.logics

import kotlinx.coroutines.delay

suspend fun animationEffect(start:Int, end:Int, final:String):String{
    val duration=3000L
    val interval=500L
    val range=start..end
    val startTime=System.currentTimeMillis()

    var number =0

    for (i in range){
        if (System.currentTimeMillis()-startTime>=duration)break
        number=i
        delay(interval)
    }

    return number.toString()
}