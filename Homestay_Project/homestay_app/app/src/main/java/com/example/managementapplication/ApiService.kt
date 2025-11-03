package com.example.managementapplication

import com.example.managementapplication.auth.LoginRequest
import com.example.managementapplication.auth.LoginResponse
import com.example.managementapplication.room_service.edit_room.RoomType
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // Giữ nguyên hàm login dùng Call nếu bạn muốn
    @POST("api/auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    // --- CÁC HÀM MỚI --- 
    // Chuyển sang suspend function, Retrofit sẽ tự động xử lý background thread
    // Bỏ Call<> và trả về trực tiếp List<RoomType>
    @GET("api/room-types")
    suspend fun getAllRoomTypes(): List<RoomType>

    @POST("api/room-types")
    suspend fun createRoomType(@Body roomTypeData: RoomType): RoomType

    @PUT("api/room-types/{id}")
    suspend fun updateRoomType(
        @Path("id") roomTypeId: String,
        @Body roomTypeData: RoomType
    ): RoomType
}