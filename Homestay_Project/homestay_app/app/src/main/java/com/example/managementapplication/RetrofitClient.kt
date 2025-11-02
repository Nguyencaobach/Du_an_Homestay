package com.example.managementapplication // (Gói của bạn)

import com.example.managementapplication.auth.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// "object" tạo ra một file duy nhất (singleton) để cả app dùng chung
object RetrofitClient {

    // --- CỰC KỲ QUAN TRỌNG ---
    // IP 10.0.2.2 là địa chỉ đặc biệt để máy ảo Android
    // "nhìn thấy" localhost (127.0.0.1) của MÁY TÍNH BẠN
    // (Vì server Node.js của bạn đang chạy ở localhost:3000)
    private const val BASE_URL = "http://10.0.2.2:3000/"

    // Tạo Interceptor để log chi tiết API ra Logcat (giúp debug)
    // Nó sẽ cho bạn thấy chính xác app gửi gì và server trả về gì
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Tạo OkHttp Client và thêm Interceptor
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    // Khởi tạo Retrofit (chỉ 1 lần)
    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient) // Gắn OkHttp client
            .addConverterFactory(GsonConverterFactory.create()) // Dùng Gson
            .build()

        // Tạo ra đối tượng ApiService từ file interface ta đã làm ở Bước 5
        retrofit.create(ApiService::class.java)
    }
}