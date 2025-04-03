package com.fajar.nestednavigation.feature.auth.domain.usecase

import com.fajar.nestednavigation.core.data.Resource
import com.fajar.nestednavigation.feature.auth.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {
    fun loginWithEmailAndPassword(
        email: String,
        password: String,
    ): Flow<Resource<User>>
}