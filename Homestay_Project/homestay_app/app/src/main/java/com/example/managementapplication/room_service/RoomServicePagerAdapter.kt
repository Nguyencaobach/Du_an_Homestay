

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.managementapplication.room_service.BookRoomFragment
import com.example.managementapplication.room_service.edit_room.EditRoomFragment

class RoomServicePagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    // Số lượng tab bạn có
    override fun getItemCount(): Int {
        return 2
    }

    // Quyết định Fragment nào sẽ hiển thị ở vị trí nào
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> EditRoomFragment()    // Tab 0 -> Màn hình Sửa phòng
            1 -> BookRoomFragment()     // Tab 1 -> Màn hình Đặt phòng
            else -> throw IllegalStateException("Vị trí tab không hợp lệ")
        }
    }
}