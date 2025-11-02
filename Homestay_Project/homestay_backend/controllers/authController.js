// File: controllers/authController.js

const db = require('../db/config');
// const bcrypt = require('bcryptjs'); // <<<--- ĐÃ XÓA BCRYPT
const jwt = require('jsonwebtoken');

// --- HÀM ĐĂNG KÝ (ĐÃ BỎ BĂM) ---
// (Dùng nếu bạn muốn tạo user mới mà không băm)
exports.register = async (req, res) => {
    try {
        const { username, password, email } = req.body;

        const userExists = await db.query("SELECT * FROM users WHERE username = $1 OR email = $2", [username, email]);
        if (userExists.rows.length > 0) {
            return res.status(200).json({ success: false, message: "Username hoặc Email đã tồn tại" });
        }

        // !!! SỬA LỖI: Không băm nữa, lưu thẳng mật khẩu gốc
        const password_hash = password; // Chỉ gán thẳng

        const newUser = await db.query(
            "INSERT INTO users (username, email, password_hash) VALUES ($1, $2, $3) RETURNING id, username",
            [username, email, password_hash] // Lưu mật khẩu gốc
        );

        res.status(201).json({ 
            success: true, 
            message: "Tạo tài khoản thành công (mật khẩu không băm)", 
            user: newUser.rows[0] 
        });

    } catch (err) {
        console.error(err.message);
        res.status(500).json({ success: false, message: "Lỗi server" });
    }
};

// --- HÀM LOGIN (ĐÃ BỎ BĂM) ---
exports.login = async (req, res) => {
    
    console.log("\n--- YÊU CẦU LOGIN MỚI (KHÔNG BĂM) ---");
    try {
        const { username, password } = req.body;
        console.log("1. App Android gửi:", req.body);

        // 2. Tìm user trong DB
        const result = await db.query(
            "SELECT * FROM users WHERE username = $1", 
            [username]
        );

        if (result.rows.length === 0) {
            console.log("2. KẾT QUẢ: Không tìm thấy user!");
            return res.status(200).json({ success: false, message: "Sai tên đăng nhập hoặc mật khẩu" });
        }
        
        const user = result.rows[0];
        console.log("2. KẾT QUẢ: Đã tìm thấy user.");

        // --- ĐÂY LÀ THAY ĐỔI QUAN TRỌNG NHẤT ---
        // 3. So sánh mật khẩu GỐC (từ app) với mật khẩu GỐC (từ DB)
        console.log(`3. Đang so sánh: Mật khẩu app gửi ('${password}') VỚI Mật khẩu trong DB ('${user.password_hash}')`);
        
        // So sánh 2 chuỗi text đơn giản
        const validPassword = (password === user.password_hash); // <<<--- ĐÃ BỎ BCRYPT
        
        console.log("4. Kết quả so sánh (==):", validPassword); // Sẽ báo 'true'

        if (!validPassword) {
            console.log("5. KẾT LUẬN: Mật khẩu SAIII!");
            return res.status(200).json({ success: false, message: "Sai tên đăng nhập hoặc mật khẩu" });
        }

        // ---------------------------------------------

        console.log("5. KẾT LUẬN: Mật khẩu ĐÚNG! Đang tạo token...");
        const token = jwt.sign(
            { id: user.id, username: user.username }, 
            process.env.JWT_SECRET || "bi-mat-cua-ban-o-day", 
            { expiresIn: '1h' }
        );

        res.status(200).json({
            success: true,
            message: "Đăng nhập thành công",
            token: token
        });

    } catch (err) {
        console.error("!!! ĐÃ CÓ LỖI 500 !!!:", err.message);
        res.status(500).json({ success: false, message: "Lỗi server" });
    }
};