package com.example.managementapplication.room_service.edit_room

import com.example.managementapplication.ApiService
import com.example.managementapplication.RetrofitClient

class RoomTypeRepository {

    private val apiService: ApiService by lazy {
        RetrofitClient.instance
    }

    suspend fun getAllRoomTypes() = apiService.getAllRoomTypes()

    suspend fun createRoomType(roomType: RoomType) = apiService.createRoomType(roomType)

    suspend fun updateRoomType(id: String, roomType: RoomType) = apiService.updateRoomType(id, roomType)

    // HÀM MỚI
    suspend fun deleteRoomType(id: String) = apiService.deleteRoomType(id)
}