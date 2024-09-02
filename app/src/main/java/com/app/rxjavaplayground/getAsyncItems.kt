package com.app.rxjavaplayground

import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit
import kotlin.random.Random

fun <T: Any> Observable<T>.getAsyncItems(): Observable<T> {
    return concatMap {
        val delay = Random.nextInt(5)
        Observable.just(it)
            .delay(delay.toLong(), TimeUnit.SECONDS)
    }
}