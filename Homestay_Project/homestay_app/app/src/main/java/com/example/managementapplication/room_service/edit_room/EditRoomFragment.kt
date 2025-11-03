package com.example.managementapplication.room_service.edit_room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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

    private val viewModel: RoomTypeViewModel by viewModels()
    private lateinit var roomTypeAdapter: RoomTypeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.room_service_fragment_edit_room, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews(view)
        setupRecyclerView()
        observeViewModel()

        viewModel.loadRoomTypes()
    }

    private fun setupViews(view: View) {
        etTypeId = view.findViewById(R.id.etTypeId)
        etName = view.findViewById(R.id.etName)
        etPrice = view.findViewById(R.id.etPrice)
        etDescription = view.findViewById(R.id.etDescription)
        btnAdd = view.findViewById(R.id.btnAdd)
        roomTypeRecyclerView = view.findViewById(R.id.roomTypeRecyclerView)

        btnAdd.setOnClickListener {
            handleAddButtonClick()
        }
    }

    private fun setupRecyclerView() {
        roomTypeAdapter = RoomTypeAdapter()
        roomTypeRecyclerView.adapter = roomTypeAdapter
        roomTypeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeViewModel() {
        viewModel.roomTypes.observe(viewLifecycleOwner, Observer {
            roomTypes -> roomTypeAdapter.submitList(roomTypes)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            isLoading -> // Thêm ProgressBar sau
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            error -> Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
        })
    }

    private fun handleAddButtonClick() {
        val id = etTypeId.text.toString()
        val name = etName.text.toString()
        val price = etPrice.text.toString().toDoubleOrNull()
        val description = etDescription.text.toString()

        if (id.isBlank() || name.isBlank() || price == null) {
            Toast.makeText(requireContext(), "ID, Name, and Price are required", Toast.LENGTH_SHORT).show()
            return
        }

        val newRoomType = RoomType(id, name, price, description)
        viewModel.addRoomType(newRoomType)
        clearInputFields()
    }

    private fun clearInputFields() {
        etTypeId.text.clear()
        etName.text.clear()
        etPrice.text.clear()
        etDescription.text.clear()
    }
}