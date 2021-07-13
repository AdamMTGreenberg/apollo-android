package com.apollographql.apollo.interceptor.factory

import com.apollographql.apollo.interceptor.ApolloInterceptor
import com.apollographql.apollo.interceptor.ApolloInterceptorFactory
import com.apollographql.apollo.interceptor.params.ApolloCacheInterceptorParams
import com.apollographql.apollo.internal.interceptor.ApolloCacheInterceptor

class ApolloCacheInterceptorFactory : ApolloInterceptorFactory<ApolloCacheInterceptorParams> {
  override fun newInterceptor(params: ApolloCacheInterceptorParams): ApolloInterceptor? {
    return ApolloCacheInterceptor(
        params.apolloStore,
        params.responseFieldMapper,
        params.dispatcher,
        params.logger,
        params.writeToCacheAsynchronously
    )
  }
}