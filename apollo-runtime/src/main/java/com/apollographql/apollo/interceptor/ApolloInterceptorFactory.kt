package com.apollographql.apollo.interceptor

import com.apollographql.apollo.interceptor.params.ApolloInterceptorFactoryParams

interface ApolloInterceptorFactory<P : ApolloInterceptorFactoryParams> {
  /**
   * creates a new interceptor for the given operation
   *
   * @param params factory parameters for creating the [ApolloInterceptor]
   *
   * @return the interceptor or null if no interceptor is needed for this operation
   */
  fun newInterceptor(params: P): ApolloInterceptor?
}