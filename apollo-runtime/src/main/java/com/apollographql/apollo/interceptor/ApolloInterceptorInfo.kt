package com.apollographql.apollo.interceptor

import com.apollographql.apollo.subscription.BatchingReport
import com.apollographql.apollo.subscription.CacheReport
import com.apollographql.apollo.subscription.ParseReport
import com.apollographql.apollo.subscription.ServerReport

interface ApolloInterceptorInfo {
  fun onBatchingReport(report: BatchingReport)
  fun onCacheReport(report: CacheReport<*>)
  fun onParseReport(report: ParseReport)
  fun onServerReport(report: ServerReport)
}