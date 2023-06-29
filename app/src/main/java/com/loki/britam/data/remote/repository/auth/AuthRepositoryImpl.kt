package com.loki.britam.data.remote.repository.auth

import com.loki.britam.data.remote.VisionApi
import com.loki.britam.domain.models.Login
import com.loki.britam.domain.models.Register
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: VisionApi
): AuthRepository {

    override suspend fun login(login: Login) {
        api.login(login)
    }

    override suspend fun register(register: Register) {
        api.register(register)
    }
}