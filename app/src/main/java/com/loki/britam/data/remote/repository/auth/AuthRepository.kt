package com.loki.britam.data.remote.repository.auth

import com.loki.britam.domain.models.Login
import com.loki.britam.domain.models.Register

interface AuthRepository {

    suspend fun login(login: Login)

    suspend fun register(register: Register)
}