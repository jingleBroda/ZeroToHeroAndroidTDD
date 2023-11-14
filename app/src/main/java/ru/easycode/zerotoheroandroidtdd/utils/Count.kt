package ru.easycode.zerotoheroandroidtdd.utils

interface Count {
    fun increment(number: String): UiState

    class Base(
        private val step: Int,
        private val max: Int
    ): Count {

        init {
            if(step <= 0) throw IllegalStateException("step should be positive, but was $step")
            if(max <= 0) throw IllegalStateException("max should be positive, but was $max")
            if(step > max) throw IllegalStateException("max should be more than step")
        }

        override fun increment(number: String): UiState {
            val result = number.toInt() + step
            if(result > max) throw IllegalStateException("max $max < increment $result")
            else {
                return if(result + step <= max)
                    UiState.Base(
                        result.toString()
                    )
                else
                    UiState.Max(
                        result.toString()
                    )
            }
        }
    }
}