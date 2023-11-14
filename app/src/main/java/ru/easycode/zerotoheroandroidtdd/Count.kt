package ru.easycode.zerotoheroandroidtdd

abstract class Count(protected val step: Int) {
    init {
        if(step <=0) throw IllegalStateException("step should be positive, but was $step")
    }
    abstract fun increment(number: String): String

    class Base(step: Int) : Count(step) {
        override fun increment(number: String): String = (number.toInt() + step).toString()
    }
}