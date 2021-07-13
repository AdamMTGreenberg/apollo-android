package com.apollographql.apollo.interceptor.params

import com.apollographql.apollo.api.cache.http.HttpCachePolicy
import okhttp3.Call
import okhttp3.HttpUrl
import com.apollographql.apollo.api.internal.ApolloLogger
import com.apollographql.apollo.api.ScalarTypeAdapters

data class ApolloServerInterceptorParams(
    val serverUrl: HttpUrl,
    val httpCallFactory: Call.Factory,
    val cachePolicy: HttpCachePolicy.Policy?,
    val prefetch: Boolean,
    val scalarTypeAdapters: ScalarTypeAdapters,
    val logger: ApolloLogger
) : ApolloInterceptorFactoryParams