package com.apollographql.apollo.interceptor.strategy

import com.apollographql.apollo.interceptor.ApolloInterceptor
import com.apollographql.apollo.interceptor.params.ApolloParseInterceptorParams
import com.apollographql.apollo.internal.interceptor.ApolloParseInterceptor

class ApolloParseInterceptorStrategy : ApolloInterceptorStrategy<ApolloParseInterceptorParams> {
  override fun newInterceptor(params: ApolloParseInterceptorParams): ApolloInterceptor {
    return ApolloParseInterceptor(params.httpCache, params.normalizer, params.responseFieldMapper, params.scalarTypeAdapters, params.logger)
  }
}