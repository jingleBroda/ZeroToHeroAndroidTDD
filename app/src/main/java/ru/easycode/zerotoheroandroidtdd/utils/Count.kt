package ru.easycode.zerotoheroandroidtdd.utils

interface Count {
    fun initial(number: String): UiState
    fun increment(number: String): UiState
    fun decrement(number: String): UiState

    class Base(
        val step: Int,
        val max: Int,
        val min: Int
    ): Count {
        init{
            if(step < 1)
                throw IllegalStateException("step should be positive, but was $step")
            if(max < 1)
                throw IllegalStateException("max should be positive, but was $max")
            if(step > max)
                throw IllegalStateException("max should be more than step")
            if(min > max)
                throw IllegalStateException("max should be more than min")
        }

        override fun initial(number: String): UiState =
            when(number.toInt()) {
                max-> UiState.Max(number)
                min-> UiState.Min(number)
                else -> UiState.Base(number)
            }

        override fun increment(number: String): UiState {
            val numInt = number.toInt()
            val result = numInt + step

            return if(result < max)
                return UiState.Base(result.toString())
            else UiState.Max(max.toString())
        }

        override fun decrement(number: String): UiState {
            val numInt = number.toInt()
            val result = numInt - step

            return if(result > min)
                return UiState.Base(result.toString())
            else UiState.Min(min.toString())
        }
    }
}