package com.apollographql.apollo.interceptor.strategy

import com.apollographql.apollo.interceptor.ApolloInterceptor
import com.apollographql.apollo.interceptor.params.EmptyInterceptorParams
import com.apollographql.apollo.internal.fetcher.CacheOnlyFetcher

/**
 * Factory class responsible for creating the [CacheOnlyFetcher.CacheOnlyInterceptor]
 */
class CacheOnlyInterceptorStrategy : ApolloInterceptorStrategy<EmptyInterceptorParams> {

  override fun newInterceptor(params: EmptyInterceptorParams): ApolloInterceptor {
    return CacheOnlyFetcher.CacheOnlyInterceptor()
  }
}