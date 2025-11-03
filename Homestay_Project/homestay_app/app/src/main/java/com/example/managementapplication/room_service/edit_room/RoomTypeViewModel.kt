package com.example.managementapplication.room_service.edit_room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RoomTypeViewModel : ViewModel() {

    private val repository = RoomTypeRepository()

    private val _roomTypes = MutableLiveData<List<RoomType>>()
    val roomTypes: LiveData<List<RoomType>> = _roomTypes

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun loadRoomTypes() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Gọi trực tiếp suspend function
                val result = repository.getAllRoomTypes()
                _roomTypes.value = result
            } catch (e: Exception) {
                _error.value = "Failed to load room types: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addRoomType(roomType: RoomType) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.createRoomType(roomType)
                loadRoomTypes() // Tải lại danh sách sau khi thêm
            } catch (e: Exception) {
                _error.value = "Failed to add room type: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}