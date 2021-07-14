package com.apollographql.apollo.interceptor.strategy

import com.apollographql.apollo.interceptor.ApolloInterceptor
import com.apollographql.apollo.interceptor.params.ApolloCacheInterceptorParams
import com.apollographql.apollo.internal.interceptor.ApolloCacheInterceptor

class ApolloCacheInterceptorStrategy : ApolloInterceptorStrategy<ApolloCacheInterceptorParams> {
  override fun newInterceptor(params: ApolloCacheInterceptorParams): ApolloInterceptor {
    return ApolloCacheInterceptor(
        params.apolloStore,
        params.responseFieldMapper,
        params.dispatcher,
        params.logger,
        params.writeToCacheAsynchronously
    )
  }
}