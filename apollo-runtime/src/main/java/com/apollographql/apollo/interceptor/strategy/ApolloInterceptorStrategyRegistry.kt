package com.apollographql.apollo.interceptor.strategy

import com.apollographql.apollo.interceptor.APOLLO_AUTO_PERSISTED_OPERATION_INTERCEPTOR
import com.apollographql.apollo.interceptor.APOLLO_BATCHING_INTERCEPTOR
import com.apollographql.apollo.interceptor.APOLLO_CACHE_INTERCEPTOR
import com.apollographql.apollo.interceptor.APOLLO_PARSE_INTERCEPTOR
import com.apollographql.apollo.interceptor.APOLLO_SERVER_INTERCEPTOR
import com.apollographql.apollo.interceptor.ApolloInterceptor
import com.apollographql.apollo.interceptor.CACHE_AND_NETWORK_INTERCEPTOR
import com.apollographql.apollo.interceptor.CACHE_FIRST_INTERCEPTOR
import com.apollographql.apollo.interceptor.CACHE_ONLY_INTERCEPTOR
import com.apollographql.apollo.interceptor.NETWORK_FIRST_INTERCEPTOR
import com.apollographql.apollo.interceptor.NETWORK_ONLY_INTERCEPTOR
import com.apollographql.apollo.interceptor.params.ApolloAutoPersistedOperationInterceptorParams
import com.apollographql.apollo.interceptor.params.ApolloBatchingInterceptorParams
import com.apollographql.apollo.interceptor.params.ApolloCacheInterceptorParams
import com.apollographql.apollo.interceptor.params.ApolloInterceptorStrategyParams
import com.apollographql.apollo.interceptor.params.ApolloParseInterceptorParams
import com.apollographql.apollo.interceptor.params.ApolloServerInterceptorParams
import com.apollographql.apollo.interceptor.params.EmptyInterceptorParams
import com.apollographql.apollo.interceptor.params.LoggerInterceptorParams
import java.util.concurrent.ConcurrentHashMap

/**
 * Registry singleton that allows a default registration of [ApolloInterceptorStrategy] so that they can be matched and fetched accordingly
 */
object ApolloInterceptorStrategyRegistry {

  private val STRATEGY_REGISTRY: MutableMap<Int, ApolloInterceptorStrategyLocator<out ApolloInterceptorStrategyParams>>
      = ConcurrentHashMap<Int, ApolloInterceptorStrategyLocator<out ApolloInterceptorStrategyParams>>()

  init {
    // Default set of ApolloInterceptors registered with this system
    STRATEGY_REGISTRY[NETWORK_FIRST_INTERCEPTOR] =
        ApolloInterceptorStrategyLocator<LoggerInterceptorParams>(NetworkFirstInterceptorStrategy())
    STRATEGY_REGISTRY[CACHE_ONLY_INTERCEPTOR] =
        ApolloInterceptorStrategyLocator<EmptyInterceptorParams>(CacheOnlyInterceptorStrategy())
    STRATEGY_REGISTRY[APOLLO_PARSE_INTERCEPTOR] =
        ApolloInterceptorStrategyLocator<ApolloParseInterceptorParams>(ApolloParseInterceptorStrategy())
    STRATEGY_REGISTRY[NETWORK_ONLY_INTERCEPTOR] =
        ApolloInterceptorStrategyLocator<EmptyInterceptorParams>(NetworkOnlyInterceptorStrategy())
    STRATEGY_REGISTRY[CACHE_AND_NETWORK_INTERCEPTOR] =
        ApolloInterceptorStrategyLocator<EmptyInterceptorParams>(CacheAndNetworkInterceptorStrategy())
    STRATEGY_REGISTRY[APOLLO_AUTO_PERSISTED_OPERATION_INTERCEPTOR] =
        ApolloInterceptorStrategyLocator<ApolloAutoPersistedOperationInterceptorParams>(ApolloAutoPersistedOperationInterceptorStrategy())
    STRATEGY_REGISTRY[APOLLO_CACHE_INTERCEPTOR] =
        ApolloInterceptorStrategyLocator<ApolloCacheInterceptorParams>(ApolloCacheInterceptorStrategy())
    STRATEGY_REGISTRY[CACHE_FIRST_INTERCEPTOR] =
        ApolloInterceptorStrategyLocator<EmptyInterceptorParams>(CacheFirstInterceptorStrategy())
    STRATEGY_REGISTRY[APOLLO_SERVER_INTERCEPTOR] =
        ApolloInterceptorStrategyLocator<ApolloServerInterceptorParams>(ApolloServerInterceptorStrategy())
    STRATEGY_REGISTRY[APOLLO_BATCHING_INTERCEPTOR] =
        ApolloInterceptorStrategyLocator<ApolloBatchingInterceptorParams>(ApolloBatchingInterceptorStrategy())
  }

  /**
   * Register a new [ApolloInterceptorStrategy] to allow for a new default factory behavior, register a new one, or remove a currently
   * registered factory.
   * @param factoryId identifier of the [ApolloInterceptorStrategy]
   * @param apolloInterceptor the [ApolloInterceptor] to register, or null to remove the currently registered factory from
   * from the mappings
   */
  internal fun register(
      factoryId: Int,
      apolloInterceptor: ApolloInterceptor?
  ) {
    synchronized(STRATEGY_REGISTRY) {
      if (apolloInterceptor == null) {
        STRATEGY_REGISTRY.remove(factoryId) // TODO probably add back the default ApolloInterceptorStrategy
      } else {
        STRATEGY_REGISTRY[factoryId]?.let {
          // Allow for overriding of the default behaviors once
          if (it.interceptor == null) {
            it.interceptor = apolloInterceptor
          }
        }
      }
    }
  }

  /**
   * Register a batch of [ApolloInterceptor] to allow for a new default factory behavior, register a new one, or remove a currently
   * registered factory.
   * @param apolloInterceptors the list of [ApolloInterceptor] to register, or null to remove the currently registered factory from
   * from the mappings
   */
  internal fun register(
      apolloInterceptors: List<ApolloInterceptor>
  ) {
    apolloInterceptors.forEach {
      register(it.interceptorId, it)
    }
  }

  /**
   * Retrieve the concrete [ApolloInterceptorFactory] associated with the passed in identifier if one exists
   * @param factoryId the identifier for the default [ApolloInterceptor] associated with that interceptor's behaviour
   * @return the [ApolloInterceptor] or null if nothing associated exists
   */
  internal fun <F: ApolloInterceptorStrategyParams> provideInterceptor(factoryId: Int, params: F?): ApolloInterceptor? {
    synchronized(STRATEGY_REGISTRY) { return STRATEGY_REGISTRY[factoryId]?.newApolloInterceptorInstance(params) }
  }
}