package com.example.managementapplication.other_service

import RoomServicePagerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.managementapplication.databinding.ActivityOtherServiceBinding
import com.google.android.material.tabs.TabLayoutMediator

class OtherServiceActivity : AppCompatActivity() {

    // Dùng ViewBinding
    private lateinit var binding: ActivityOtherServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Nạp layout (ví dụ: activity_room_service.xml)
        binding = ActivityOtherServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Khởi tạo Adapter
        val pagerAdapter = OtherServicePagerAdapter(this)

        // 2. Gán Adapter cho ViewPager2
        binding.viewPager.adapter = pagerAdapter

        // 3. Kết nối TabLayout với ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            // Set tên cho từng tab
            when (position) {
                0 -> tab.text = "Dining Area"
                1 -> tab.text = "Car Rental"
            }
        }.attach()
    }
}