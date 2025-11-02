// File: db/config.js

const { Pool } = require('pg');
// (Không cần dotenv ở đây)

// 1. Tạo Pool kết nối
// process.env.DATABASE_URL đã tồn tại do server.js chạy trước
const pool = new Pool({
    connectionString: process.env.DATABASE_URL,
    
    // Azure yêu cầu SSL
    ssl: {
        rejectUnauthorized: false
    }
});

// 2. Kiểm tra kết nối (log ra console)
pool.connect((err, client, release) => {
    if (err) {
        return console.error('Lỗi khi kết nối đến PostgreSQL (Azure)', err.stack);
    }
    console.log('Đã kết nối thành công đến PostgreSQL (Azure)!');
    client.release(); // Trả client về pool
});

// 3. Xuất ra hàm query để các file khác dùng
module.exports = {
    query: (text, params) => pool.query(text, params),
};