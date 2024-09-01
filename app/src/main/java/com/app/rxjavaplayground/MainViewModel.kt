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

    fun creationOperators(operator: Operator) {
        when (operator) {
            Operator.Interval -> showCaseInterval()
            Operator.Just -> showCaseJust()
            Operator.Range -> showCaseRange()
            Operator.Repeat -> showCaseRepeat()
            Operator.FlatMap -> showCaseFlatMap()
        }
    }

    private fun showCaseInterval() {
        val observable = Observable.interval(1, TimeUnit.SECONDS)

        val observer = object : Observer<Long> {
            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
                Log.d(TAG, "onSubscribe called for showCaseInterval")
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                Log.d(TAG, "onError called for showCaseInterval")
            }

            override fun onComplete() {
                Log.d(TAG, "onComplete called for showCaseInterval")
            }

            override fun onNext(t: Long) {
                Log.d(TAG, "onNext called for showCaseInterval with value: $t")
                if (t == 20L)
                    onComplete()
            }
        }

        observable.subscribe(observer)
    }

    private fun showCaseJust() {

    }

    private fun showCaseRange() {
        val observable = Observable.range(0, 10).repeat(2)

        val observer = object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
                Log.d(TAG, "onSubscribe called for showCaseRange")
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                Log.d(TAG, "onError called for showCaseRange")
            }

            override fun onComplete() {
                Log.d(TAG, "onComplete called for showCaseRange")
            }

            override fun onNext(t: Int) {
                Log.d(TAG, "onNext called for showCaseRange with value: $t")
            }
        }

        observable.subscribe(observer)
    }

    private fun showCaseRepeat() {

    }

    private fun showCaseFlatMap() {
        val observable = Observable.just(12, 13, 16)

        val observer = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
                Log.d(TAG, "onSubscribe called for showCaseFlatMap")
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                Log.d(TAG, "onError called for showCaseFlatMap")
            }

            override fun onComplete() {
                Log.d(TAG, "onComplete called for showCaseFlatMap")
            }

            override fun onNext(t: String) {
                Log.d(TAG, "onNext called for showCaseFlatMap with value: $t")
            }
        }

        observable
            .flatMap { id ->
                getProfileName(id)
            }
            .subscribe(observer)
    }

    private fun getProfileName(id: Int): Observable<String> {
        return when (id) {
            12 -> Observable.just("getProfileName $id")
            13 -> Observable.just("getProfileName $id")
            else -> Observable.error(Throwable("Some error popped up"))
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}