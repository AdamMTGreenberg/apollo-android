package com.apollographql.apollo.interceptor.params

import com.apollographql.apollo.api.cache.http.HttpCache
import com.apollographql.apollo.api.ScalarTypeAdapters
import com.apollographql.apollo.api.internal.ApolloLogger
import com.apollographql.apollo.api.internal.ResponseFieldMapper
import com.apollographql.apollo.cache.normalized.internal.ResponseNormalizer

data class ApolloParseInterceptorParams(
    val httpCache: HttpCache,
    val normalizer: ResponseNormalizer<Map<String?, Any?>?>,
    val responseFieldMapper: ResponseFieldMapper,
    val scalarTypeAdapters: ScalarTypeAdapters,
    val logger: ApolloLogger
) : ApolloInterceptorFactoryParams
