package com.app.rxjavaplayground

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

class MainViewModel : ViewModel() {

    private val TAG = "MainViewModel"

    private val compositeDisposable = CompositeDisposable()

    private val observer = object : Observer<String> {
        override fun onSubscribe(d: Disposable) {
            compositeDisposable.add(d)
            Log.d(TAG, "OnSubscribe called")
        }

        override fun onError(e: Throwable) {
            Log.d(TAG, "onError called for Observable")
            e.printStackTrace()
        }

        override fun onComplete() {
            Log.d(TAG, "onComplete called")
        }

        override fun onNext(t: String) {
            Log.d(TAG, "onNext called with value $t")
        }
    }

    private val singleObserver = object : SingleObserver<String> {
        override fun onSubscribe(d: Disposable) {
            compositeDisposable.add(d)
        }

        override fun onError(e: Throwable) {
            Log.d(TAG, "onError called for Single Observable")
            e.printStackTrace()
        }

        override fun onSuccess(t: String) {
            Log.d(TAG, "onSuccess called with value $t")
        }

    }

    fun showDataFlowOfRxJavaUsingObservable() {
        val observable = Observable.just(
            1,
            2,
            3,
        ).timeInterval(TimeUnit.SECONDS).map {
            "Item ${it.time()} from Observable"
        }
        observable.subscribe(observer)
    }

    fun showDataFlowOfRxJavaUsingSingle() {
        val observable = Single.just(
            1,
        ).timeInterval(TimeUnit.SECONDS).map {
            "Item ${it.time()} from Single"
        }
        observable.subscribe(singleObserver)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}