package com.example.managementapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.LinearLayout // <-- Đảm bảo bạn đã import LinearLayout
import com.example.managementapplication.other_service.OtherServiceActivity
import com.example.managementapplication.room_service.RoomServiceActivity

class HomeActivity : AppCompatActivity() {

    // Khai báo các nút là LinearLayout
    private lateinit var roomServiceButton: LinearLayout
    private lateinit var dashboardButton: LinearLayout
    private lateinit var otherServiceButton: LinearLayout

    private lateinit var locationButton: LinearLayout
    private lateinit var messageButton: LinearLayout
    private lateinit var customerButton: LinearLayout

    private lateinit var websiteButton: LinearLayout

    private lateinit var costsButton: LinearLayout

    private lateinit var instructionButton: LinearLayout


    // Hàm onCreate được gọi khi Activity được tạo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home) // Đảm bảo tên layout là chính xác

        // --- 1. Xử lý nút Room Service ---
        // Ánh xạ View: Tìm LinearLayout bằng ID
        roomServiceButton = findViewById(R.id.roomServiceBtn) // <-- ID này giờ là của LinearLayout

        roomServiceButton.setOnClickListener {
            // Tạo Intent để chuyển đến RoomServiceActivity
            val intent = Intent(this, RoomServiceActivity::class.java)
            startActivity(intent)
        }

        // --- 2. Xử lý nút Dashboard ---
        dashboardButton = findViewById(R.id.dashboardBtn) // <-- ID này giờ là của LinearLayout

        dashboardButton.setOnClickListener {
            // Tạo Intent để chuyển đến DashboardActivity
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        // --- 3. Xử lý nút Other Service ---
        otherServiceButton = findViewById(R.id.otherServiceBtn) // <-- ID này giờ là của LinearLayout

        otherServiceButton.setOnClickListener {
            val intent = Intent(this, OtherServiceActivity::class.java)
            startActivity(intent)
        }

        // --- 4. Xử ly nút Location ---

        locationButton = findViewById(R.id.locationBtn)
        locationButton.setOnClickListener {
             val intent = Intent(this, LocationActivity::class.java)
             startActivity(intent)
        }

        // --- 5. Xử ly nút Message ---
        messageButton = findViewById(R.id.messageBtn)
        messageButton.setOnClickListener {
             val intent = Intent(this, MessageActivity::class.java)
             startActivity(intent)
        }

        // --- 6. Xử ly nút Customer ---
        customerButton = findViewById(R.id.customerBtn)
        customerButton.setOnClickListener {
             val intent = Intent(this, CustomerActivity::class.java)
             startActivity(intent)
        }

        // --- 7. Xử ly nút Website ---
        websiteButton = findViewById(R.id.websiteBtn)
        websiteButton.setOnClickListener {
             // val intent = Intent(this, WebsiteActivity::class.java)
             // startActivity(intent)
        }

        // --- 8. Xử ly nút Costs ---
        costsButton = findViewById(R.id.costsBtn)
        costsButton.setOnClickListener {
             val intent = Intent(this, CostsActivity::class.java)
             startActivity(intent)
        }

        // --- 9. Xử ly nút Instruction ---
        instructionButton = findViewById(R.id.instructionBtn)
        instructionButton.setOnClickListener {
             val intent = Intent(this, InstructionActivity::class.java)
             startActivity(intent)
        }
    }
}