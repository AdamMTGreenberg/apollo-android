package com.apollographql.apollo.interceptor.factory

import com.apollographql.apollo.interceptor.ApolloInterceptor
import com.apollographql.apollo.interceptor.ApolloInterceptorFactory
import com.apollographql.apollo.interceptor.params.LoggerInterceptorParams
import com.apollographql.apollo.internal.fetcher.NetworkFirstFetcher

/**
 * Factory class responsible for creating the [NetworkFirstFetcher.NetworkFirstInterceptor]
 */
class NetworkFirstInterceptorFactory : ApolloInterceptorFactory<LoggerInterceptorParams> {

  override fun newInterceptor(params: LoggerInterceptorParams): ApolloInterceptor? {
    return NetworkFirstFetcher.NetworkFirstInterceptor(params.logger)
  }
}