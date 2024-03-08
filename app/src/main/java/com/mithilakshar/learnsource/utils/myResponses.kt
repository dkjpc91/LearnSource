package com.mithilakshar.learnsource.utils

sealed class myResponses<T>(val data:T?=null,
                            val errorMessage: String? = null,
                            val progress: Int = 0) {

    class Loading<T>(private val mProgress: Int = 0) : myResponses<T>(progress = mProgress)

    class Success<T>(private val mData: T?) : myResponses<T>(data = mData)

    class Error<T>(private val mErrMessage: String?) : myResponses<T>(errorMessage = mErrMessage)
}