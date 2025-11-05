package com.example.managementapplication.room_service

import RoomServicePagerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.managementapplication.databinding.ActivityRoomServiceBinding
import com.google.android.material.tabs.TabLayoutMediator

class RoomServiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoomServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pagerAdapter = RoomServicePagerAdapter(this)
        binding.viewPager.adapter = pagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Room Edit"
                1 -> tab.text = "Detail Room Edit"
            }
        }.attach()
    }
}