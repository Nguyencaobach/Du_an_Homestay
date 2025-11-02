package com.example.managementapplication.auth

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val token: String? // '?' nghĩa là token có thể có hoặc không (null)
)