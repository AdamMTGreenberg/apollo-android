package com.apollographql.apollo.interceptor

import com.apollographql.apollo.interceptor.strategy.ApolloBatchingInterceptorStrategy
import com.apollographql.apollo.interceptor.strategy.ApolloCacheInterceptorStrategy
import com.apollographql.apollo.interceptor.strategy.ApolloParseInterceptorStrategy
import com.apollographql.apollo.interceptor.strategy.ApolloServerInterceptorStrategy
import com.apollographql.apollo.interceptor.strategy.CacheAndNetworkInterceptorStrategy
import com.apollographql.apollo.interceptor.strategy.CacheFirstInterceptorStrategy
import com.apollographql.apollo.interceptor.strategy.CacheOnlyInterceptorStrategy
import com.apollographql.apollo.interceptor.strategy.NetworkFirstInterceptorStrategy
import com.apollographql.apollo.interceptor.strategy.NetworkOnlyInterceptorStrategy
import com.apollographql.apollo.interceptor.params.ApolloInterceptorStrategyParams
import com.apollographql.apollo.interceptor.strategy.ApolloAutoPersistedOperationInterceptorStrategy
import com.apollographql.apollo.interceptor.strategy.ApolloInterceptorStrategy
import java.util.concurrent.ConcurrentHashMap

/**
 * Registry singleton that allows a default registration of [ApolloInterceptorStrategy] so that they can be matched and fetched accordingly
 */
object ApolloInterceptorStrategyRegistry {

  private val STRATEGY_REGISTRY: MutableMap<Int, ApolloInterceptorStrategy<out ApolloInterceptorStrategyParams>>
      = ConcurrentHashMap<Int, ApolloInterceptorStrategy<out ApolloInterceptorStrategyParams>>()

  init {
    STRATEGY_REGISTRY[NETWORK_FIRST_INTERCEPTOR] = NetworkFirstInterceptorStrategy()
    STRATEGY_REGISTRY[CACHE_ONLY_INTERCEPTOR] = CacheOnlyInterceptorStrategy()
    STRATEGY_REGISTRY[APOLLO_PARSE_INTERCEPTOR] = ApolloParseInterceptorStrategy()
    STRATEGY_REGISTRY[NETWORK_ONLY_INTERCEPTOR] = NetworkOnlyInterceptorStrategy()
    STRATEGY_REGISTRY[CACHE_AND_NETWORK_INTERCEPTOR] = CacheAndNetworkInterceptorStrategy()
    STRATEGY_REGISTRY[APOLLO_AUTO_PERSISTED_OPERATION_INTERCEPTOR] = ApolloAutoPersistedOperationInterceptorStrategy()
    STRATEGY_REGISTRY[APOLLO_CACHE_INTERCEPTOR] = ApolloCacheInterceptorStrategy()
    STRATEGY_REGISTRY[CACHE_FIRST_INTERCEPTOR] = CacheFirstInterceptorStrategy()
    STRATEGY_REGISTRY[APOLLO_SERVER_INTERCEPTOR] = ApolloServerInterceptorStrategy()
    STRATEGY_REGISTRY[APOLLO_BATCHING_INTERCEPTOR] = ApolloBatchingInterceptorStrategy()
  }

  /**
   * Register a new [ApolloInterceptorStrategy] to allow for a new default factory behavior, register a new one, or remove a currently
   * registered factory.
   * @param factoryId identifier of the [ApolloInterceptorStrategy]
   * @param apolloInterceptorStrategy the [ApolloInterceptorStrategy] to register, or null to remove the currently registered factory from
   * from the mappings
   */
  internal fun register(
      factoryId: Int,
      apolloInterceptorStrategy: ApolloInterceptorStrategy<out ApolloInterceptorStrategyParams>?
  ) {
    synchronized(STRATEGY_REGISTRY) {
      if (apolloInterceptorStrategy == null) {
        STRATEGY_REGISTRY.remove(factoryId)
      } else {
        STRATEGY_REGISTRY[factoryId] = apolloInterceptorStrategy
      }
    }
  }

  /**
   * Retrieve the concrete [ApolloInterceptorFactory] associated with the passed in identifier if one exists
   * @param factoryId the identifier for the default [ApolloInterceptorFactory] associated with that interceptor's behavour
   * @return the [ApolloInterceptorFactory] or null if nothing associated exists
   */
  internal fun <F: ApolloInterceptorStrategy<out ApolloInterceptorStrategyParams>?> provideInterceptor(factoryId: Int): F? { // Don't love this pattern but want a concrete type and can't inline
    synchronized(STRATEGY_REGISTRY) { return STRATEGY_REGISTRY[factoryId] as F? }
  }
}