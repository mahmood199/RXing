package com.app.rxjavaplayground

sealed interface Operator {

    data object Just: Operator

    data object Range: Operator

    data object Repeat: Operator

    data object Interval: Operator

    data object FlatMap: Operator

}