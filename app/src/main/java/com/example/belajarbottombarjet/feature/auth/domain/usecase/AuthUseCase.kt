package com.example.belajarbottombarjet.feature.auth.domain.usecase

import com.example.belajarbottombarjet.core.data.Resource
import com.example.belajarbottombarjet.feature.auth.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {
    fun loginWithEmailAndPassword(
        email: String,
        password: String,
    ): Flow<Resource<User>>
}