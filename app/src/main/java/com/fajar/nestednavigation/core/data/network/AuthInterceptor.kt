package com.fajar.nestednavigation.core.data.network

import android.content.Context
import android.util.Log
import com.fajar.nestednavigation.core.data.DataStoreManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val  context: Context
) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { dataStoreManager.getUserInfo().first()?.token }
        val request = chain.request().newBuilder()
        if (token != null) {
            request.addHeader("Authorization", "Bearer $token")
        }

        val response = chain.proceed(request.build())
        if (response.code == 401){
            Log.d(TAG, "intercept: token expired")
            runBlocking {
                dataStoreManager.clearUserInfo()
            }
//            val intent = Intent(context, AuthActivity::class.java).apply {
//                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            }
//            context.startActivity(intent)
        }
        return response
    }

    companion object {
        const val TAG = "AuthInterceptor"
    }
}