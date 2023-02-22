package com.senecaglobal.weatherapp.di

import com.senecaglobal.weatherapp.BuildConfig
import com.senecaglobal.weatherapp.network.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
        OkHttpClient.Builder()
            //.addInterceptor(AuthInterceptor(MyApplication.applicationContext()))
            .addInterceptor(loggingInterceptor)
            .hostnameVerifier { _, _ -> true }
            .build()
    } else {
        OkHttpClient
            .Builder()
            .hostnameVerifier { _, _ -> true }
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideWeatherApiService(retrofit: Retrofit) = retrofit.create(WeatherApi::class.java)

}