package com.apollographql.apollo.interceptor.strategy

import com.apollographql.apollo.interceptor.ApolloAutoPersistedOperationInterceptor
import com.apollographql.apollo.interceptor.ApolloInterceptor
import com.apollographql.apollo.interceptor.params.ApolloAutoPersistedOperationInterceptorParams

class ApolloAutoPersistedOperationInterceptorStrategy : ApolloInterceptorStrategy<ApolloAutoPersistedOperationInterceptorParams> {
  override fun newInterceptor(params: ApolloAutoPersistedOperationInterceptorParams): ApolloInterceptor {
    return ApolloAutoPersistedOperationInterceptor(params.logger, params.useHttpGetMethodForPersistedOperations)
  }
}