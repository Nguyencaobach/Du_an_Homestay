package com.example.managementapplication.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.managementapplication.HomeActivity
import com.example.managementapplication.auth.LoginRequest
import com.example.managementapplication.auth.LoginResponse
import com.example.managementapplication.R
import com.example.managementapplication.RetrofitClient
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    // Khai báo view ở đây để dùng trong cả class
    private lateinit var usernameEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login) // (Tên file layout của bạn)

        // 1. Ánh xạ các view
        // (Hãy đảm bảo ID trong file XML của bạn khớp với các ID này)
        usernameEditText = findViewById(R.id.edittext_username)
        passwordEditText = findViewById(R.id.edittext_password)
        loginButton = findViewById(R.id.loginBtn)

        // 2. Xử lý sự kiện click
        loginButton.setOnClickListener {
            // Lấy text từ ô nhập
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Kiểm tra xem người dùng đã nhập đủ chưa
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Dừng lại nếu chưa nhập đủ
            }

            // Tạo đối tượng Request từ data class ta đã tạo
            val loginRequest = LoginRequest(username, password)

            // Gọi hàm thực hiện API
            performLogin(loginRequest)
        }
    }

    // Hàm riêng để gọi API
    private fun performLogin(request: LoginRequest) {
        // Lấy Retrofit client từ file object ta vừa tạo
        val call = RetrofitClient.instance.login(request)

        // Thực thi cuộc gọi (bất đồng bộ - chạy ngầm)
        call.enqueue(object : Callback<LoginResponse> {

            // Xử lý khi CÓ kết quả trả về (Kể cả khi server báo lỗi 404, 500...)
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    // Server trả về mã 2xx (ví dụ: 200 OK)
                    val loginResponse = response.body()

                    if (loginResponse != null && loginResponse.success) {
                        // --- ĐĂNG NHẬP THÀNH CÔNG ---
                        Toast.makeText(this@LoginActivity, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show()

                        // TODO: Lưu token (loginResponse.token) lại
                        // (Bạn sẽ cần SharedPreferences để làm việc này sau)

                        // Chuyển sang màn hình HomeActivity
                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        startActivity(intent)
                        finish() // Đóng màn hình Login để người dùng không back lại được

                    } else {
                        // Đăng nhập thất bại (sai user/pass)
                        // Hiển thị message từ server (ví dụ: "Sai tên đăng nhập...")
                        Toast.makeText(this@LoginActivity, loginResponse?.message ?: "Sai thông tin", Toast.LENGTH_LONG).show()
                    }
                } else {
                    // Server trả về mã lỗi (401, 404, 500...)
                    Toast.makeText(this@LoginActivity, "Lỗi server: ${response.code()}", Toast.LENGTH_LONG).show()
                }
            }

            // Xử lý khi KHÔNG thể kết nối (mất mạng, sập server, sai URL)
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // In lỗi ra Logcat để debug
                Log.e("LoginActivity", "Lỗi kết nối: ${t.message}", t)
                Toast.makeText(this@LoginActivity, "Lỗi kết nối: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}