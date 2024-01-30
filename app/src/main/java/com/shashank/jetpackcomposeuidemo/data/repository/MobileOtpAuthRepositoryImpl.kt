package com.shashank.jetpackcomposeuidemo.data.repository

import com.shashank.jetpackcomposeuidemo.core.utils.Resource
import com.shashank.jetpackcomposeuidemo.core.utils.SendOTPModel
import com.shashank.jetpackcomposeuidemo.core.utils.VerifyOtpModel
import com.shashank.jetpackcomposeuidemo.data.api.MobileOtpAuthApi
import com.shashank.jetpackcomposeuidemo.data.mapper.toDomainMobileAuthOtp
import com.shashank.jetpackcomposeuidemo.domain.model.MobileAuthOtp
import com.shashank.jetpackcomposeuidemo.domain.repository.MobileOtpAuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MobileOtpAuthRepositoryImpl @Inject constructor(private val mobileOtpAuthApi: MobileOtpAuthApi): MobileOtpAuthRepository {
    override fun sendOTP(sendOTPModel: SendOTPModel): Flow<Resource<MobileAuthOtp>> = flow {
        emit(Resource.Loading())
        val result = mobileOtpAuthApi.sendOTP(sendOTPModel).toDomainMobileAuthOtp()
        emit(Resource.Success(result))
    }.flowOn(Dispatchers.IO)
        .catch {
            emit(Resource.Error(it.message.toString()))
        }

    override fun verifyOTP(verifyOtpModel: VerifyOtpModel): Flow<Resource<MobileAuthOtp>> = flow {
        emit(Resource.Loading())
        val result = mobileOtpAuthApi.verifyOtp(verifyOtpModel).toDomainMobileAuthOtp()
        emit(Resource.Success(result))
    }.flowOn(Dispatchers.IO)
        .catch {
            emit(Resource.Error(it.message.toString()))
        }

}