// File: controllers/roomTypeController.js
const RoomType = require('../models/roomType');

exports.getAllRoomTypes = async (req, res) => {
  try {
    const result = await RoomType.getAll();
    res.status(200).json(result.rows);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

exports.createRoomType = async (req, res) => {
  try {
    // req.body chứa dữ liệu JSON gửi từ app Android
    const result = await RoomType.create(req.body);
    res.status(201).json(result.rows[0]);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

exports.updateRoomType = async (req, res) => {
    try {
        const { id } = req.params; // Lấy id từ URL (ví dụ: /api/room-types/LP001)
        const result = await RoomType.update(id, req.body);

        if (result.rows.length === 0) {
            return res.status(404).json({ message: 'Room type not found' });
        }
        res.status(200).json(result.rows[0]);
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
}

exports.deleteRoomType = async (req, res) => {
    try {
        const { id } = req.params; // Lấy id từ URL
        await RoomType.deleteById(id);
        // Trả về 204 No Content - là mã chuẩn cho việc xóa thành công
        res.status(204).send();
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
}