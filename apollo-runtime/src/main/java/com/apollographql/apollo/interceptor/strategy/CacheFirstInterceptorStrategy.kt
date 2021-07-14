package com.apollographql.apollo.interceptor.strategy

import com.apollographql.apollo.interceptor.ApolloInterceptor
import com.apollographql.apollo.interceptor.params.EmptyInterceptorParams
import com.apollographql.apollo.internal.fetcher.CacheFirstFetcher

class CacheFirstInterceptorStrategy : ApolloInterceptorStrategy<EmptyInterceptorParams> {
  override fun newInterceptor(params: EmptyInterceptorParams): ApolloInterceptor {
    return CacheFirstFetcher.CacheFirstInterceptor()
  }
}