package com.example.managementapplication.room_service.edit_room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.managementapplication.R
// SỬA LẠI IMPORT CHO ĐÚNG
import com.example.managementapplication.databinding.RoomServiceFragmentEditRoomTypeBinding

class EditRoomFragment : Fragment() {

    // SỬA LẠI KIỂU DỮ LIỆU BINDING CHO ĐÚNG
    private var _binding: RoomServiceFragmentEditRoomTypeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RoomTypeViewModel by viewModels()
    private lateinit var roomTypeAdapter: RoomTypeAdapter

    private var selectedRoomTypeId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // SỬA LẠI KHỞI TẠO BINDING CHO ĐÚNG
        _binding = RoomServiceFragmentEditRoomTypeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupClickListeners()
        observeViewModel()

        viewModel.loadRoomTypes()
    }

    private fun setupClickListeners() {
        binding.btnAdd.setOnClickListener { handleAddButtonClick() }
        binding.btnEdit.setOnClickListener { handleUpdateButtonClick() }
    }

    private fun setupRecyclerView() {
        roomTypeAdapter = RoomTypeAdapter(
            onEditClick = { roomType ->
                viewModel.onRoomTypeSelected(roomType)
            },
            onDeleteClick = { roomType ->
                showDeleteConfirmationDialog(roomType)
            }
        )
        binding.roomTypeRecyclerView.adapter = roomTypeAdapter
        binding.roomTypeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeViewModel() {
        viewModel.roomTypes.observe(viewLifecycleOwner, Observer { roomTypes ->
            roomTypeAdapter.submitList(roomTypes)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            // Xử lý hiển thị ProgressBar sau
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
        })

        viewModel.selectedRoomType.observe(viewLifecycleOwner, Observer { roomType ->
            binding.etTypeId.setText(roomType.id)
            binding.etName.setText(roomType.name)
            binding.etPrice.setText(roomType.price.toString())
            binding.etDescription.setText(roomType.description)

            selectedRoomTypeId = roomType.id
            binding.etTypeId.isEnabled = false
            binding.btnAdd.text = "Clear Form"
        })
    }

    private fun handleAddButtonClick() {
        if (binding.btnAdd.text.toString().equals("Clear Form", ignoreCase = true)) {
            clearInputFields()
            return
        }
        
        val id = binding.etTypeId.text.toString()
        val name = binding.etName.text.toString()
        val price = binding.etPrice.text.toString().toDoubleOrNull()
        val description = binding.etDescription.text.toString()

        if (id.isBlank() || name.isBlank() || price == null) {
            Toast.makeText(requireContext(), "ID, Name, and Price are required", Toast.LENGTH_SHORT).show()
            return
        }

        val newRoomType = RoomType(id, name, price, description)
        viewModel.addRoomType(newRoomType)
        clearInputFields()
    }

    private fun handleUpdateButtonClick() {
        val idToUpdate = selectedRoomTypeId
        if (idToUpdate == null) {
            Toast.makeText(requireContext(), "Please select a room type to edit first", Toast.LENGTH_SHORT).show()
            return
        }

        val name = binding.etName.text.toString()
        val price = binding.etPrice.text.toString().toDoubleOrNull()
        val description = binding.etDescription.text.toString()

        if (name.isBlank() || price == null) {
            Toast.makeText(requireContext(), "Name and Price cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        val updatedRoomType = RoomType(idToUpdate, name, price, description)
        viewModel.updateRoomType(idToUpdate, updatedRoomType)
        clearInputFields()
    }

    private fun showDeleteConfirmationDialog(roomType: RoomType) {
        AlertDialog.Builder(requireContext())
            .setTitle("Confirm Delete")
            .setMessage("Are you sure you want to delete '${roomType.name}'?")
            .setPositiveButton("Delete") { _, _ ->
                viewModel.deleteRoomType(roomType.id)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun clearInputFields() {
        binding.etTypeId.text.clear()
        binding.etName.text.clear()
        binding.etPrice.text.clear()
        binding.etDescription.text.clear()

        selectedRoomTypeId = null
        binding.etTypeId.isEnabled = true
        binding.btnAdd.text = "Add New"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}