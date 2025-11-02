package com.example.managementapplication.room_service.edit_room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.managementapplication.R

class RoomTypeAdapter(private val roomTypes: MutableList<RoomType>) : RecyclerView.Adapter<RoomTypeAdapter.RoomTypeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomTypeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.room_type_item, parent, false)
        return RoomTypeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomTypeViewHolder, position: Int) {
        val roomType = roomTypes[position]
        holder.bind(roomType)
    }

    override fun getItemCount() = roomTypes.size

    fun addRoomType(roomType: RoomType) {
        roomTypes.add(roomType)
        notifyItemInserted(roomTypes.size - 1)
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
}
