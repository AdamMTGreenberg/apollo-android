package com.apollographql.apollo.interceptor.strategy

import com.apollographql.apollo.interceptor.ApolloInterceptor
import com.apollographql.apollo.interceptor.params.ApolloInterceptorStrategyParams

interface ApolloInterceptorStrategy<P : ApolloInterceptorStrategyParams> {

  /**
   * creates a new [ApolloInterceptor] for the given operation. Differs from the [ApolloInterceptorFactory] in order
   * to preserve public facing APIs.
   *
   * @param params factory parameters for creating the [ApolloInterceptor]
   *
   * @return the interceptor or null if no interceptor is needed for this operation
   */
  fun newInterceptor(params: P): ApolloInterceptor
}