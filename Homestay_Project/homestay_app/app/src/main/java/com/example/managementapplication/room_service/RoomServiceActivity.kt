package com.example.managementapplication.room_service

import RoomServicePagerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.managementapplication.databinding.ActivityRoomServiceBinding // <-- Import ViewBinding
import com.google.android.material.tabs.TabLayoutMediator

class RoomServiceActivity : AppCompatActivity() {

    // Dùng ViewBinding
    private lateinit var binding: ActivityRoomServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Nạp layout (ví dụ: activity_room_service.xml)
        binding = ActivityRoomServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Khởi tạo Adapter
        val pagerAdapter = RoomServicePagerAdapter(this)

        // 2. Gán Adapter cho ViewPager2
        binding.viewPager.adapter = pagerAdapter

        // 3. Kết nối TabLayout với ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            // Set tên cho từng tab
            when (position) {
                0 -> tab.text = "Room Edit"
                1 -> tab.text = "Room Booking"
            }
        }.attach()
    }
}