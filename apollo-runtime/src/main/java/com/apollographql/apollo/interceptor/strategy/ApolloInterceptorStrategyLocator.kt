package com.apollographql.apollo.interceptor.strategy

import com.apollographql.apollo.interceptor.ApolloInterceptor
import com.apollographql.apollo.interceptor.params.ApolloInterceptorStrategyParams

class ApolloInterceptorStrategyLocator<P: ApolloInterceptorStrategyParams>(
    private val strategy: ApolloInterceptorStrategy<P>,
    var interceptor: ApolloInterceptor? = null
) {
    fun newApolloInterceptorInstance(params: P?): ApolloInterceptor? {
      return interceptor ?: if (params != null) { strategy.newInterceptor(params)} else { null }
    }
}