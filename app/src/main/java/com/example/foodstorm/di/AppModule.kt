package com.example.foodstorm.di

import com.example.foodstorm.data.remote.FoodApi
import com.example.foodstorm.repository.FoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://api.spoonacular.com/"
    private const val API_KEY = "f7f060578b994261b44edcc77a9bf358"

    @Singleton
    @Provides
    fun provideFoodRepository(
        api: FoodApi
    ) = FoodRepository(api)


    @Singleton
    @Provides
    fun provideFoodApi(): FoodApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            var original = chain.request()
            val url = original.url.newBuilder()
                .addQueryParameter("apiKey", API_KEY).build()
            original = original.newBuilder().url(url).build()
            chain.proceed(original)
        })
        httpClient.addInterceptor(interceptor)
        val client: OkHttpClient = httpClient.build()
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                    .create(FoodApi::class.java)


    }


}