package com.example.managementapplication.auth

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

import com.example.managementapplication.room_service.edit_room.RoomType
import retrofit2.http.GET
// (Gói của bạn)

interface ApiService {

    /**
     * Định nghĩa API đăng nhập.
     * @POST: Sử dụng phương thức POST.
     * "api/auth/login": Đây là đường dẫn (endpoint) trên server Node.js của bạn.
     * (Nó được nối với BASE_URL)
     */
    @POST("api/auth/login")
    fun login(
        // @Body: Gửi đối tượng LoginRequest (gồm username, password)
        //        trong phần thân (body) của request
        @Body request: LoginRequest
    ): Call<LoginResponse> // Kiểu dữ liệu mong đợi nhận về là LoginResponse

    // --- THÊM CÁC HÀM MỚI CHO "LOẠI PHÒNG" VÀO ĐÂY ---
    /**
     * API 1: Lấy tất cả Loại phòng
     */
    @GET("api/room-types")
    fun getAllRoomTypes(): Call<List<RoomType>>

    /**
     * API 2: Tạo một Loại phòng mới
     */
    @POST("api/room-types")
    fun createRoomType(@Body roomTypeData: RoomType): Call<RoomType>
}