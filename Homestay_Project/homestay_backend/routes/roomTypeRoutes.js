// File: routes/roomTypeRoutes.js
const express = require('express');
const router = express.Router();
const roomTypeController = require('../controllers/roomTypeController');

// GET http://localhost:3000/api/room-types
router.get('/room-types', roomTypeController.getAllRoomTypes);

// POST http://localhost:3000/api/room-types
router.post('/room-types', roomTypeController.createRoomType);

// PUT http://localhost:3000/api/room-types/:id
router.put('/room-types/:id', roomTypeController.updateRoomType);

module.exports = router;