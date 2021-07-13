package com.apollographql.apollo.interceptor.factory

import com.apollographql.apollo.interceptor.ApolloInterceptor
import com.apollographql.apollo.interceptor.ApolloInterceptorFactory
import com.apollographql.apollo.interceptor.params.ApolloServerInterceptorParams
import com.apollographql.apollo.internal.interceptor.ApolloServerInterceptor

class ApolloServerInterceptorFactory : ApolloInterceptorFactory<ApolloServerInterceptorParams> {
  override fun newInterceptor(params: ApolloServerInterceptorParams): ApolloInterceptor? {
    return ApolloServerInterceptor(params.serverUrl, params.httpCallFactory, params.cachePolicy, params.prefetch,
        params.scalarTypeAdapters, params.logger)
  }
}