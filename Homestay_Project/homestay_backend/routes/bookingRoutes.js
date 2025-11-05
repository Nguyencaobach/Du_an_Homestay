// File: routes/bookingRoutes.js
const express = require('express');
const router = express.Router();
const bookingController = require('../controllers/bookingController');

// GET /api/available-rooms
router.get('/available-rooms', bookingController.getAvailableRooms);

// POST /api/bookings
router.post('/bookings', bookingController.createBooking);

module.exports = router;