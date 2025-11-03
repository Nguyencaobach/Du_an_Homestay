package com.example.managementapplication.room_service.edit_room

import com.example.managementapplication.ApiService
import com.example.managementapplication.RetrofitClient

class RoomTypeRepository {

    // Lấy instance của ApiService
    private val apiService: ApiService by lazy {
        RetrofitClient.instance
    }

    // Các hàm này giờ sẽ gọi trực tiếp suspend function từ ApiService
    suspend fun getAllRoomTypes() = apiService.getAllRoomTypes()

    suspend fun createRoomType(roomType: RoomType) = apiService.createRoomType(roomType)

    suspend fun updateRoomType(id: String, roomType: RoomType) = apiService.updateRoomType(id, roomType)
}