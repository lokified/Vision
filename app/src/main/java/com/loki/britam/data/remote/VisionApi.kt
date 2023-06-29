package com.loki.britam.data.remote

import com.loki.britam.domain.models.Login
import com.loki.britam.domain.models.Register
import retrofit2.http.Body
import retrofit2.http.POST

interface VisionApi {

    @POST("")
    suspend fun login(
        @Body login: Login
    )

    @POST("")
    suspend fun register(
        @Body register: Register
    )
}