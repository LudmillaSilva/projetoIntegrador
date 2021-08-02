package com.ludmilla.integratorproject.data.factory


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    fun getService() {
/*
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("apikey", Constants.PUBLIC_KEY.value)
                .build()

            chain.proceed(original.newBuilder().url(url).build())
*/
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
//           .client(httpClient.build())
            .build()



}