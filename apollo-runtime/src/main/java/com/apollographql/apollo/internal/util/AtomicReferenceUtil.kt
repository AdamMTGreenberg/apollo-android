package com.apollographql.apollo.internal.util

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.internal.subscription.ApolloInterceptorInfoPipeline
import java.util.concurrent.atomic.AtomicReference

fun <T> AtomicReference<ApolloCall.Callback<T>>.get(pipeline: ApolloInterceptorInfoPipeline<T>?): ApolloCall.Callback<T>? {
  val ref = this.get()
  pipeline?.let{ ref?.setPipeline(it) }
  return ref
}

fun <T> AtomicReference<ApolloCall.Callback<T>>.set(
    value: ApolloCall.Callback<T>?,
    pipeline: ApolloInterceptorInfoPipeline<T>?
) {
  if (value != null && pipeline != null) { value.setPipeline(pipeline) }
  this.set(value)
}


fun <T> AtomicReference<ApolloCall.Callback<T>>.getAndSet(
    value: ApolloCall.Callback<T>?,
    pipeline: ApolloInterceptorInfoPipeline<T>?
): ApolloCall.Callback<T>? {
  if (value != null && pipeline != null) { value.setPipeline(pipeline) }
  return this.getAndSet(value)
}