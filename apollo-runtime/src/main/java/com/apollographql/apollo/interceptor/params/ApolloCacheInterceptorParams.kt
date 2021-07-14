package com.apollographql.apollo.interceptor.params

import java.util.concurrent.Executor
import com.apollographql.apollo.cache.normalized.ApolloStore
import com.apollographql.apollo.api.internal.ResponseFieldMapper
import com.apollographql.apollo.api.internal.ApolloLogger

data class ApolloCacheInterceptorParams(
    val apolloStore: ApolloStore,
    val responseFieldMapper: ResponseFieldMapper,
    val dispatcher: Executor,
    val logger: ApolloLogger,
    val writeToCacheAsynchronously: Boolean
) : ApolloInterceptorStrategyParams
