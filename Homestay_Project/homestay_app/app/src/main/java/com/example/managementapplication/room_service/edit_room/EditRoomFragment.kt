package com.example.managementapplication.room_service.edit_room

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.managementapplication.R

class EditRoomFragment : Fragment() {

    private lateinit var etTypeId: EditText
    private lateinit var etName: EditText
    private lateinit var etPrice: EditText
    private lateinit var etDescription: EditText
    private lateinit var btnAdd: Button
    private lateinit var roomTypeRecyclerView: RecyclerView
    private lateinit var roomTypeAdapter: RoomTypeAdapter
    private val roomTypes = mutableListOf<RoomType>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.room_service_fragment_edit_room, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etTypeId = view.findViewById(R.id.etTypeId)
        etName = view.findViewById(R.id.etName)
        etPrice = view.findViewById(R.id.etPrice)
        etDescription = view.findViewById(R.id.etDescription)
        btnAdd = view.findViewById(R.id.btnAdd)
        roomTypeRecyclerView = view.findViewById(R.id.roomTypeRecyclerView)

        roomTypeAdapter = RoomTypeAdapter(roomTypes)
        roomTypeRecyclerView.adapter = roomTypeAdapter
        roomTypeRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        btnAdd.setOnClickListener {
            val id = etTypeId.text.toString()
            val name = etName.text.toString()
            val price = etPrice.text.toString().toDoubleOrNull() ?: 0.0
            val description = etDescription.text.toString()

            if (id.isNotEmpty() && name.isNotEmpty()) {
                val roomType = RoomType(id, name, price, description)
                roomTypeAdapter.addRoomType(roomType)
                clearInputFields()
            }
        }
    }

    private fun clearInputFields() {
        etTypeId.text.clear()
        etName.text.clear()
        etPrice.text.clear()
        etDescription.text.clear()
    }
}