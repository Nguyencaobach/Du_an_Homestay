package com.example.managementapplication.room_service.edit_room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.managementapplication.R

class RoomTypeAdapter(
    private val onEditClick: (RoomType) -> Unit,
    private val onDeleteClick: (RoomType) -> Unit
) : ListAdapter<RoomType, RoomTypeAdapter.RoomTypeViewHolder>(RoomTypeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomTypeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.room_service_fragment_edit_room_type_item, parent, false)
        return RoomTypeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomTypeViewHolder, position: Int) {
        val roomType = getItem(position)
        holder.bind(roomType, onEditClick, onDeleteClick)
    }

    class RoomTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvRoomName: TextView = itemView.findViewById(R.id.tvRoomName)
        private val tvRoomPrice: TextView = itemView.findViewById(R.id.tvRoomPrice)
        private val tvRoomDescription: TextView = itemView.findViewById(R.id.tvRoomDescription)
        private val btnEdit: Button = itemView.findViewById(R.id.btnItemEdit)
        private val btnDelete: Button = itemView.findViewById(R.id.btnItemDelete)

        fun bind(roomType: RoomType, onEditClick: (RoomType) -> Unit, onDeleteClick: (RoomType) -> Unit) {
            tvRoomName.text = roomType.name
            tvRoomPrice.text = "Price: ${roomType.price}"
            tvRoomDescription.text = roomType.description

            btnEdit.setOnClickListener { onEditClick(roomType) }
            btnDelete.setOnClickListener { onDeleteClick(roomType) }
        }
    }

    class RoomTypeDiffCallback : DiffUtil.ItemCallback<RoomType>() {
        override fun areItemsTheSame(oldItem: RoomType, newItem: RoomType): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RoomType, newItem: RoomType): Boolean {
            return oldItem == newItem
        }
    }
}