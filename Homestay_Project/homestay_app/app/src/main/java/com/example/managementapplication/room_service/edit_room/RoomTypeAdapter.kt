package com.example.managementapplication.room_service.edit_room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.managementapplication.R

class RoomTypeAdapter : ListAdapter<RoomType, RoomTypeAdapter.RoomTypeViewHolder>(RoomTypeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomTypeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.room_type_item, parent, false)
        return RoomTypeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomTypeViewHolder, position: Int) {
        val roomType = getItem(position)
        holder.bind(roomType)
    }

    class RoomTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvRoomName: TextView = itemView.findViewById(R.id.tvRoomName)
        private val tvRoomPrice: TextView = itemView.findViewById(R.id.tvRoomPrice)
        private val tvRoomDescription: TextView = itemView.findViewById(R.id.tvRoomDescription)

        fun bind(roomType: RoomType) {
            tvRoomName.text = roomType.name
            tvRoomPrice.text = "Price: ${roomType.price}"
            tvRoomDescription.text = roomType.description
        }
    }

    // Class này giúp ListAdapter biết item nào đã thay đổi
    class RoomTypeDiffCallback : DiffUtil.ItemCallback<RoomType>() {
        override fun areItemsTheSame(oldItem: RoomType, newItem: RoomType): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RoomType, newItem: RoomType): Boolean {
            return oldItem == newItem
        }
    }
}