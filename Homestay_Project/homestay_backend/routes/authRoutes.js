// File: routes/authRoutes.js

const express = require('express');
const router = express.Router();
const authController = require('../controllers/authController'); // Import controller

// Đường dẫn http://.../api/auth/register
router.post('/register', authController.register);

// Đường dẫn http://.../api/auth/login
router.post('/login', authController.login);

module.exports = router;