package co.movie.app.core.http.interceptor

import co.movie.app.BuildConfig
import co.movie.app.core.http.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = chain.request().url.newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)

        return kotlin.runCatching {
            chain.proceed(
                originalRequest
                    .newBuilder()
                    .url(
                        request.build()
                    )
                    .build()
            )
        }.onFailure {
            throw NoConnectivityException()
        }.getOrThrow()
    }
}