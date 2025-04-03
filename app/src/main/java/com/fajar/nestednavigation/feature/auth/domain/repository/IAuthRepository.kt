package com.fajar.nestednavigation.feature.auth.domain.repository

import com.fajar.nestednavigation.core.data.Resource
import com.fajar.nestednavigation.feature.auth.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    fun loginWithEmailAndPassword(
        email: String,
        password: String,
    ): Flow<Resource<User>>
}