package com.apollographql.apollo.interceptor.strategy

import com.apollographql.apollo.interceptor.ApolloInterceptor
import com.apollographql.apollo.interceptor.params.EmptyInterceptorParams
import com.apollographql.apollo.internal.fetcher.CacheAndNetworkFetcher

class CacheAndNetworkInterceptorStrategy : ApolloInterceptorStrategy<EmptyInterceptorParams>{
  override fun newInterceptor(params: EmptyInterceptorParams): ApolloInterceptor {
   return CacheAndNetworkFetcher.CacheAndNetworkInterceptor()
  }
}