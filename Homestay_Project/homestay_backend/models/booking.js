// File: models/booking.js
const db = require('../db/config');

// Lấy tất cả các phòng đang sẵn sàng (available)
const getAvailableRooms = () => {
  return db.query("SELECT r.id, r.room_number, rt.name as type_name, rt.price FROM rooms r JOIN room_types rt ON r.type_id = rt.id WHERE r.status = 'available' ORDER BY r.room_number");
};

// Tạo một booking mới
const create = ({ room_id, customer_name, check_in_date, check_out_date, total_price }) => {
  const queryText = 'INSERT INTO bookings(room_id, customer_name, check_in_date, check_out_date, total_price) VALUES($1, $2, $3, $4, $5) RETURNING *';
  return db.query(queryText, [room_id, customer_name, check_in_date, check_out_date, total_price]);
};

module.exports = { getAvailableRooms, create };