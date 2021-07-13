package com.apollographql.apollo.interceptor.params

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.internal.ApolloLogger

/**
 * @param logger: a logger to output debug information
 * @param operation: the operation
 */
data class ApolloAutoPersistedOperationInterceptorParams(
    val logger: ApolloLogger,
    val operation: Operation<*, *, *>
) : ApolloInterceptorFactoryParams