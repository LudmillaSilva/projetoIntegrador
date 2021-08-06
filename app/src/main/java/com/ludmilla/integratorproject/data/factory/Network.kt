package com.ludmilla.integratorproject.data.factory


import com.ludmilla.integratorproject.data.remotesource.RemoteSource
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    fun getService() :Retrofit {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", Constants.PUBLIC_KEY.value)
                .addQueryParameter("language", Constants.DEFAULT_LANGUAGE.value)
                .build()

            chain.proceed(original.newBuilder().url(url).build())
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            return retrofit
    }

    fun <T> applySingleTransformer():SingleTransformer<T,T> {
        return SingleTransformer { single ->
            single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun getRemoteSource():RemoteSource{
        val source = getService()
        return source.create(RemoteSource::class.java)
    }



}