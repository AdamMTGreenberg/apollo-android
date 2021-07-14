package com.apollographql.apollo.interceptor.params

import com.apollographql.apollo.internal.batch.BatchPoller

data class ApolloBatchingInterceptorParams(val batcher: BatchPoller) : ApolloInterceptorStrategyParams