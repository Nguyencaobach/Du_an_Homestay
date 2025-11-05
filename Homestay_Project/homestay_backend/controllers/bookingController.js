// File: controllers/bookingController.js
const Booking = require('../models/booking');

exports.getAvailableRooms = async (req, res) => {
  try {
    const result = await Booking.getAvailableRooms();
    res.status(200).json(result.rows);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

exports.createBooking = async (req, res) => {
  try {
    const result = await Booking.create(req.body);
    // TODO: Cập nhật trạng thái phòng thành 'occupied' sau khi đặt thành công
    res.status(201).json(result.rows[0]);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};