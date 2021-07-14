package com.apollographql.apollo.internal.subscription

import com.apollographql.apollo.interceptor.ApolloInterceptorInfo
import com.apollographql.apollo.subscription.BatchingReport
import com.apollographql.apollo.subscription.CacheReport
import com.apollographql.apollo.subscription.InterceptorReport
import com.apollographql.apollo.subscription.ParseReport
import com.apollographql.apollo.subscription.ServerReport

class ApolloInterceptorInfoPipeline<T>(
    private var apolloInterceptorInfo: ApolloInterceptorInfo?,
) {
  fun onReport(report: InterceptorReport) {
    when (report) {
      is BatchingReport -> {
        // TODO
      }
      is CacheReport<*> -> {
        apolloInterceptorInfo?.onCacheReport(report)
      }
      is ParseReport -> {
        // TODO
      }
      is ServerReport -> {
        // TODO
      }
    }
  }
}