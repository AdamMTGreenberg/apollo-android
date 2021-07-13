package com.apollographql.apollo.interceptor.params

import com.apollographql.apollo.api.internal.ApolloLogger

data class LoggerInterceptorParams(val logger: ApolloLogger) : ApolloInterceptorFactoryParams