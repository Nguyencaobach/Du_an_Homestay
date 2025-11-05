package com.example.managementapplication.room_service.edit_room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RoomTypeViewModel : ViewModel() {

    private val repository = RoomTypeRepository()

    private val _roomTypes = MutableLiveData<List<RoomType>>()
    val roomTypes: LiveData<List<RoomType>> = _roomTypes

    private val _selectedRoomType = MutableLiveData<RoomType>()
    val selectedRoomType: LiveData<RoomType> = _selectedRoomType

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun loadRoomTypes() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _roomTypes.value = repository.getAllRoomTypes()
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
                loadRoomTypes()
            } catch (e: Exception) {
                // --- PHẦN CẢI TIẾN NẰM Ở ĐÂY ---
                if (e is HttpException && e.code() == 500) {
                    _error.value = "ID này có thể đã tồn tại. Vui lòng chọn một ID khác."
                } else {
                    _error.value = "Failed to add room type: ${e.message}"
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateRoomType(id: String, roomType: RoomType) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.updateRoomType(id, roomType)
                loadRoomTypes()
            } catch (e: Exception) {
                _error.value = "Failed to update room type: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteRoomType(id: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.deleteRoomType(id)
                loadRoomTypes()
            } catch (e: Exception) {
                _error.value = "Failed to delete room type: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun onRoomTypeSelected(roomType: RoomType) {
        _selectedRoomType.value = roomType
    }
}