package com.apollographql.apollo.interceptor.factory

import com.apollographql.apollo.interceptor.ApolloInterceptor
import com.apollographql.apollo.interceptor.ApolloInterceptorFactory
import com.apollographql.apollo.interceptor.params.ApolloBatchingInterceptorParams
import com.apollographql.apollo.internal.interceptor.ApolloBatchingInterceptor

class ApolloBatchingInterceptorFactory : ApolloInterceptorFactory<ApolloBatchingInterceptorParams> {
  override fun newInterceptor(params: ApolloBatchingInterceptorParams): ApolloInterceptor? {
    return ApolloBatchingInterceptor(params.batcher)
  }
}