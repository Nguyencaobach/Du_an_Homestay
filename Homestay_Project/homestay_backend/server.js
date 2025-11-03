// File: server.js (Ở thư mục gốc)

// DÒNG NÀY PHẢI Ở ĐẦU TIÊN
const dotenv = require('dotenv');
dotenv.config(); 

// Dòng này để debug, xem .env đã được tải chưa
console.log('Biến DATABASE_URL đã được tải:', process.env.DATABASE_URL);

// --- 1. IMPORT ---
const express = require('express');
const cors = require('cors');
const db = require('./db/config'); // Import kết nối DB

// Import file router (từ Bước 4)
const authRoutes = require('./routes/authRoutes');

// --- 2. CẤU HÌNH ---
const app = express();
const PORT = process.env.PORT || 3000;

// --- 3. MIDDLEWARE ---
app.use(cors()); // Cho phép Android gọi API
app.use(express.json()); // Giúp server hiểu JSON

// --- 4. ROUTES (ĐƯỜNG DẪN) ---
app.get('/', (req, res) => {
    res.status(200).send('Homestay Backend API đang chạy!');
});

// Route debug kết nối DB
app.get('/db-test', async (req, res) => {
    try {
        const result = await db.query('SELECT NOW()'); 
        res.status(200).json({
            message: 'Kết nối database thành công!',
            time_from_db: result.rows[0].now
        });
    } catch (err) {
        res.status(500).json({
            message: 'Lỗi kết nối database!!!',
            error: err.message
        });
    }
});

// --- DÒNG QUAN TRỌNG NHẤT CỦA BẠN ---
// Gắn router vào đường dẫn /api/auth
// Mọi request tới /api/auth/login sẽ được chuyển tới authRoutes
app.use('/api/auth', authRoutes);

// Import routes
const roomTypeRoutes = require('./routes/roomTypeRoutes');

// Sử dụng routes với tiền tố /api
app.use('/api', roomTypeRoutes);


// --- 5. KHỞI CHẠY SERVER ---
app.listen(PORT, () => {
    console.log(`Server đang lắng nghe trên cổng ${PORT}`);
});