package com.shashank.jetpackcomposeuidemo.di

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.shashank.jetpackcomposeuidemo.core.utils.Constants
import com.shashank.jetpackcomposeuidemo.data.api.MobileOtpAuthApi
import com.shashank.jetpackcomposeuidemo.data.firebaseauth.repository.FirebaseAuthRepositoryImpl
import com.shashank.jetpackcomposeuidemo.data.repository.MobileOtpAuthRepositoryImpl
import com.shashank.jetpackcomposeuidemo.domain.repository.FirebaseAuthRepository
import com.shashank.jetpackcomposeuidemo.domain.repository.MobileOtpAuthRepository
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.database
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
//    @Provides
//    @Singleton
//    fun providesRealtimeDb(): DatabaseReference =
//        Firebase.database.reference.child("user")
//
//    @Singleton
//    @Provides
//    fun providesFirestoreDb(): FirebaseFirestore = Firebase.firestore

    @Singleton
    @Provides
    fun providesFirebaseAuth(): FirebaseAuth = Firebase.auth


    @Singleton
    @Provides
    fun providesFirebaseAuthRepository(firebaseAuth: FirebaseAuth): FirebaseAuthRepository {
        return FirebaseAuthRepositoryImpl(firebaseAuth)
    }

    //retrofit injections
    @Provides
    @Singleton
    fun providesLoggingInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(5, TimeUnit.MINUTES)
        .readTimeout(5, TimeUnit.MINUTES)
        .build()

    @Provides
    @Singleton
    fun provideRetrofitInstance(client: OkHttpClient) = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .client(client)
        .build()

    @Provides
    @Singleton
    fun mobileOtpAuthApi(retrofit: Retrofit) : MobileOtpAuthApi = retrofit.create(MobileOtpAuthApi::class.java)

    @Provides
    @Singleton
    fun provideMobileOtpAuthRepository(api: MobileOtpAuthApi) : MobileOtpAuthRepository {
        return MobileOtpAuthRepositoryImpl(api)
    }

    //Shared preference singleton object creation handled by Hilt
    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)
    }
}