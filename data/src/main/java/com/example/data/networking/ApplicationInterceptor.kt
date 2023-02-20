package com.example.data.networking

import okhttp3.Interceptor
import okhttp3.Response

/** Application Interceptor class for the session*/
class ApplicationInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request().newBuilder().build())
    }
}