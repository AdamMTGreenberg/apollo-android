package com.apollographql.apollo.interceptor.factory

import com.apollographql.apollo.interceptor.ApolloInterceptor
import com.apollographql.apollo.interceptor.ApolloInterceptorFactory
import com.apollographql.apollo.interceptor.params.EmptyInterceptorParams
import com.apollographql.apollo.internal.fetcher.CacheAndNetworkFetcher

class CacheAndNetworkInterceptorFactory : ApolloInterceptorFactory<EmptyInterceptorParams>{
  override fun newInterceptor(params: EmptyInterceptorParams): ApolloInterceptor? {
   return CacheAndNetworkFetcher.CacheAndNetworkInterceptor()
  }
}