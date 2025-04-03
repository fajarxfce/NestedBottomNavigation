package com.example.belajarbottombarjet.feature.auth.domain.repository

import com.example.belajarbottombarjet.core.data.Resource
import com.example.belajarbottombarjet.feature.auth.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    fun loginWithEmailAndPassword(
        email: String,
        password: String,
    ): Flow<Resource<User>>
}