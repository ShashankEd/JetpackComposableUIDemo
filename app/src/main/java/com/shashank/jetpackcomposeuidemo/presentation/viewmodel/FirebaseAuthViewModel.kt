package com.shashank.jetpackcomposeuidemo.presentation.viewmodel

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shashank.jetpackcomposeuidemo.core.utils.ResultState
import com.shashank.jetpackcomposeuidemo.domain.usecase.FirebaseAuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FirebaseAuthViewModel @Inject constructor(private val firebaseAuthUseCase: FirebaseAuthUseCase): ViewModel() {

    private val _firebaseAuthState = MutableStateFlow("")
    val firebaseAuthState get() = _firebaseAuthState

    private val _firebaseAuthVerifyState = MutableStateFlow("")
    val firebaseAuthVerifyState get() = _firebaseAuthVerifyState

    fun createUserWithPhone(phone:String, activity: Activity) {
        Log.d("Auth", "createUserWithPhone called : $phone")
        firebaseAuthUseCase.createUserWithPhone(phone, activity).onEach {
            when(it) {
                ResultState.Loading -> {
                    Log.d("Auth", "ResultState loading...")
                    _firebaseAuthState.value = it.toString()
                }
                ResultState.Failure(it.toString()) -> {
                    Log.d("Auth", "ResultState Failure... ${it.toString()}")
                    _firebaseAuthState.value = it.toString()
                }
                ResultState.Success(it) -> {
                    Log.d("Auth", "ResultState Success... ${it.toString()}")
                    _firebaseAuthState.value = it.toString()
                }
                else -> {
                    Log.d("Auth", "ResultState Unknown... ${it.toString()}")
                    _firebaseAuthState.value = it.toString()
                }
            }

        }.launchIn(viewModelScope)
    }

    fun signWithCredential(otp:String) {
        firebaseAuthUseCase.signWithCredential(otp).onEach {
            when(it) {
                ResultState.Loading -> {
                    _firebaseAuthVerifyState.value = it.toString()
                }
                ResultState.Failure(it.toString()) -> {
                    _firebaseAuthVerifyState.value = it.toString()
                }
                ResultState.Success(it) -> {
                    _firebaseAuthVerifyState.value = it.toString()
                }
                else -> {
                    _firebaseAuthVerifyState.value = it.toString()
                }
            }
        }.launchIn(viewModelScope)
    }

    fun forceReCaptcha() {
//        firebaseAuthUseCase.forceReCaptcha()
    }

    fun signWithEmailPass(email:String, pass:String) {
        firebaseAuthUseCase.signWithEmailPass(email = email, pass = pass).onEach {
            when(it) {
                ResultState.Loading -> {
                    Log.d("Auth", "signWithEmailPass: Loading...")
                }
                ResultState.Failure(it.toString()) -> {
                    Log.d("Auth", "signWithEmailPass: Failure... $it")
                }
                ResultState.Success(it) -> {
                    Log.d("Auth", "signWithEmailPass: Success...")
                }
                else -> {
                    Log.d("Auth", "signWithEmailPass: Other ... $it")
                }
            }
        }
    }

}
