package com.shashank.jetpackcomposeuidemo.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.shashank.jetpackcomposeuidemo.data.firebaseauth.repository.FirebaseAuthRepositoryImpl
import com.shashank.jetpackcomposeuidemo.domain.repository.FirebaseAuthRepository
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.database
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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

}