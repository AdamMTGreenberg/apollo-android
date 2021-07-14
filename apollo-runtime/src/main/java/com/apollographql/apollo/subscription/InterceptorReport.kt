package com.apollographql.apollo.subscription

sealed interface InterceptorReport

sealed class DefaultInterceptorReport(): InterceptorReport
data class BatchingReport(
    val startTimeMs: Long,
    val endTimeMs: Long
) : DefaultInterceptorReport()

data class CacheReport<T>(
    val startTimeMs: Long,
    val endTimeMs: Long,
    val errors: List<Error>?,
    val data: T?,
    val cacheKey: String?
) : DefaultInterceptorReport()

data class ParseReport(
    val startTimeMs: Long,
    val endTimeMs: Long
) : DefaultInterceptorReport()

data class ServerReport(
    val startTimeMs: Long,
    val endTimeMs: Long
) : DefaultInterceptorReport()