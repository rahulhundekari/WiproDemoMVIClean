package com.demo.wiprodemo.data.api

import com.demo.wiprodemo.data.entity.UserEntity
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    suspend fun fetchUser(): List<UserEntity>
}