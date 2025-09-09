import React, { useEffect, useState } from "react";
import axios from "axios";
import config from "./config";
import './style.css';

const MoodManager = () => {
  const [moods, setMoods] = useState([]);
  const [form, setForm] = useState({
    id: "",
    mood: "",
    note: ""
  });
  const [editing, setEditing] = useState(false);
  const [message, setMessage] = useState(null);

  const API_URL = config.url;

  const fetchMoods = async () => {
    try {
      const res = await axios.get(`${API_URL}/all`);
      setMoods(res.data);
    } catch (err) {
      console.error(err);
      showMessage("Failed to fetch moods", "error");
    }
  };

  useEffect(() => {
    fetchMoods();
  }, []);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      let payload = { ...form };
      if (!editing) {
        delete payload.id;
        await axios.post(`${API_URL}/add`, payload);
        showMessage("Mood added successfully", "success");
      } else {
        await axios.put(`${API_URL}/update`, payload);
        showMessage("Mood updated successfully", "success");
      }
      resetForm();
      fetchMoods();
    } catch (err) {
      console.error(err);
      showMessage("Operation failed", "error");
    }
  };

  const handleEdit = (mood) => {
    setForm(mood);
    setEditing(true);
  };

  const handleDelete = async (id) => {
    if (window.confirm("Are you sure you want to delete this mood?")) {
      try {
        await axios.delete(`${API_URL}/delete/${id}`);
        showMessage("Mood deleted successfully", "success");
        fetchMoods();
      } catch (err) {
        console.error(err);
        showMessage("Failed to delete mood", "error");
      }
    }
  };

  const resetForm = () => {
    setForm({
      id: "",
      mood: "",
      note: ""
    });
    setEditing(false);
  };

  const showMessage = (text, type) => {
    setMessage({ text, type });
    setTimeout(() => setMessage(null), 3000);
  };

  return (
    <div className="mood-container">
      <h2>😊 Mood Tracker</h2>

      {message && (
        <div className={`message-banner ${message.type}`}>
          {message.text}
        </div>
      )}

      <form onSubmit={handleSubmit}>
        <div className="form-grid">
          <input
            type="text"
            name="mood"
            placeholder="Mood (e.g. Happy, Sad)"
            value={form.mood}
            onChange={handleChange}
            required
          />
          <input
            type="text"
            name="note"
            placeholder="Note (optional)"
            value={form.note}
            onChange={handleChange}
          />
        </div>

        <div className="btn-group">
          <button type="submit" className={editing ? "btn-green" : "btn-blue"}>
            {editing ? "Update Mood" : "Add Mood"}
          </button>
          <button type="button" className="btn-gray" onClick={resetForm}>
            Clear
          </button>
        </div>
      </form>

      <h3>📋 Mood List</h3>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Mood</th>
            <th>Note</th>
            <th>Date</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {moods.length > 0 ? (
            moods.map((mood) => (
              <tr key={mood.id}>
                <td>{mood.id}</td>
                <td>{mood.mood}</td>
                <td>{mood.note}</td>
                <td>{mood.entryDate}</td>
                <td className="action-buttons">
                  <button className="btn-green" onClick={() => handleEdit(mood)}>
                    Edit
                  </button>
                  <button className="btn-red" onClick={() => handleDelete(mood.id)}>
                    Delete
                  </button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="5">No moods found</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default MoodManager;
