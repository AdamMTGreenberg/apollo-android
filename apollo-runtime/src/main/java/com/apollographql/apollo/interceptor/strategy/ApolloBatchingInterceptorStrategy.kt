package com.apollographql.apollo.interceptor.strategy

import com.apollographql.apollo.interceptor.ApolloInterceptor
import com.apollographql.apollo.interceptor.params.ApolloBatchingInterceptorParams
import com.apollographql.apollo.internal.interceptor.ApolloBatchingInterceptor

class ApolloBatchingInterceptorStrategy : ApolloInterceptorStrategy<ApolloBatchingInterceptorParams> {
  override fun newInterceptor(params: ApolloBatchingInterceptorParams): ApolloInterceptor {
    return ApolloBatchingInterceptor(params.batcher)
  }
}