package com.example.managementapplication

import com.example.managementapplication.auth.LoginRequest
import com.example.managementapplication.auth.LoginResponse
import com.example.managementapplication.room_service.edit_room.RoomType
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // ... các hàm cũ
    @POST("api/auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("api/room-types")
    suspend fun getAllRoomTypes(): List<RoomType>

    @POST("api/room-types")
    suspend fun createRoomType(@Body roomTypeData: RoomType): RoomType

    @PUT("api/room-types/{id}")
    suspend fun updateRoomType(
        @Path("id") roomTypeId: String,
        @Body roomTypeData: RoomType
    ): RoomType

    @DELETE("api/room-types/{id}")
    suspend fun deleteRoomType(@Path("id") roomTypeId: String): Response<Unit>

}