package com.fajar.nestednavigation.feature.auth.data

import com.fajar.nestednavigation.core.data.Resource
import com.fajar.nestednavigation.feature.auth.domain.model.User
import com.fajar.nestednavigation.feature.auth.domain.repository.IAuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(

): IAuthRepository{
    override fun loginWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Resource<User>> {
        return flow {
            try {
                emit(Resource.Loading())
                // Simulate network delay
                delay(2000)
                // Simulate successful login
                val user = User(
                    id = "1",
                    name = "John Doe",
                    email = email,
                    token = password,
                )
                emit(Resource.Success(user))
            } catch (e: Exception) {
                emit(Resource.Error("Login failed"))
            }
        }
    }
}