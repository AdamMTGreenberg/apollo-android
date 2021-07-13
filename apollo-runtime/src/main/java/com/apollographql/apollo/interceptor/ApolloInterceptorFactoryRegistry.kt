package com.apollographql.apollo.interceptor

import com.apollographql.apollo.interceptor.factory.ApolloBatchingInterceptorFactory
import com.apollographql.apollo.interceptor.factory.ApolloCacheInterceptorFactory
import com.apollographql.apollo.interceptor.factory.ApolloParseInterceptorFactory
import com.apollographql.apollo.interceptor.factory.ApolloServerInterceptorFactory
import com.apollographql.apollo.interceptor.factory.CacheAndNetworkInterceptorFactory
import com.apollographql.apollo.interceptor.factory.CacheFirstInterceptorFactory
import com.apollographql.apollo.interceptor.factory.CacheOnlyInterceptorFactory
import com.apollographql.apollo.interceptor.factory.NetworkFirstInterceptorFactory
import com.apollographql.apollo.interceptor.factory.NetworkOnlyInterceptorFactory
import com.apollographql.apollo.interceptor.params.ApolloInterceptorFactoryParams
import java.util.concurrent.ConcurrentHashMap

/**
 * Registry singleton that allows a default registration of [ApolloInterceptorFactory] so that they can be matched and fetched accordingly
 */
object ApolloInterceptorFactoryRegistry {

  private val factoryRegistry: MutableMap<Int, ApolloInterceptorFactory<out ApolloInterceptorFactoryParams>>
      = ConcurrentHashMap<Int, ApolloInterceptorFactory<out ApolloInterceptorFactoryParams>>()

  init {
    factoryRegistry[NETWORK_FIRST_INTERCEPTOR] = NetworkFirstInterceptorFactory()
    factoryRegistry[CACHE_ONLY_INTERCEPTOR] = CacheOnlyInterceptorFactory()
    factoryRegistry[APOLLO_PARSE_INTERCEPTOR] = ApolloParseInterceptorFactory()
    factoryRegistry[NETWORK_ONLY_INTERCEPTOR] = NetworkOnlyInterceptorFactory()
    factoryRegistry[CACHE_AND_NETWORK_INTERCEPTOR] = CacheAndNetworkInterceptorFactory()
    factoryRegistry[APOLLO_AUTO_PERSISTED_OPERATION_INTERCEPTOR] = ApolloAutoPersistedOperationInterceptor.Factory()
    factoryRegistry[APOLLO_CACHE_INTERCEPTOR] = ApolloCacheInterceptorFactory()
    factoryRegistry[CACHE_FIRST_INTERCEPTOR] = CacheFirstInterceptorFactory()
    factoryRegistry[APOLLO_SERVER_INTERCEPTOR] = ApolloServerInterceptorFactory()
    factoryRegistry[APOLLO_BATCHING_INTERCEPTOR] = ApolloBatchingInterceptorFactory()
  }

  /**
   * Register a new [ApolloInterceptorFactory] to allow for a new default factory behavior, register a new one, or remove a currently
   * registered factory.
   * @param factoryId identifier of the [ApolloInterceptorFactory]
   * @param apolloInterceptorFactory the [ApolloInterceptorFactory] to register, or null to remove the currently registered factory from
   * from the mappings
   */
  fun register(
      factoryId: Int,
      apolloInterceptorFactory: ApolloInterceptorFactory<out ApolloInterceptorFactoryParams>?
  ) {
    synchronized(factoryRegistry) {
      if (apolloInterceptorFactory == null) {
        factoryRegistry.remove(factoryId)
      } else {
        factoryRegistry[factoryId] = apolloInterceptorFactory
      }
    }
  }

  /**
   * Retrieve the concrete [ApolloInterceptorFactory] associated with the passed in identifier if one exists
   * @param factoryId the identifier for the default [ApolloInterceptorFactory] associated with that interceptor's behavour
   * @return the [ApolloInterceptorFactory] or null if nothing associated exists
   */
  fun <F: ApolloInterceptorFactory<out ApolloInterceptorFactoryParams>?> provideInterceptor(factoryId: Int): F? { // Don't love this pattern but want a concrete type and can't inline
    synchronized(factoryRegistry) { return factoryRegistry[factoryId] as F? }
  }
}