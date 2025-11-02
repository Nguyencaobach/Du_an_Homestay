package com.example.managementapplication.other_service

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OtherServicePagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    // Số lượng tab bạn có
    override fun getItemCount(): Int {
        return 2
    }

    // Quyết định Fragment nào sẽ hiển thị ở vị trí nào
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FoodTableFragment()    // Tab 0 -> Màn hình Sửa phòng
            1 -> VehicelFragment()     // Tab 1 -> Màn hình Đặt phòng
            else -> throw IllegalStateException("Vị trí tab không hợp lệ")
        }
    }
}