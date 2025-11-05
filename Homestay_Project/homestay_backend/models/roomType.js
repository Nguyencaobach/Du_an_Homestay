// File: models/roomType.js
const db = require('../db/config');

const getAll = () => {
  return db.query('SELECT * FROM room_types ORDER BY name ASC');
};const create = ({ id, name, price, description }) => {
  const queryText = 'INSERT INTO room_types(id, name, price, description) VALUES($1, $2, $3, $4) RETURNING *';
  return db.query(queryText, [id, name, price, description]);
};

const update = (id, { name, price, description }) => {
    const queryText = 'UPDATE room_types SET name = $1, price = $2, description = $3 WHERE id = $4 RETURNING *';
    return db.query(queryText, [name, price, description, id]);
}

const deleteById = (id) => {
  const queryText = 'DELETE FROM room_types WHERE id = $1';
  return db.query(queryText, [id]);
};

module.exports = {
  getAll,
  create,
  update,
  deleteById,
};