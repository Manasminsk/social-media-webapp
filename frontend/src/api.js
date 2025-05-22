import axios from 'axios';

const api = axios.create({
  baseURL: process.env.REACT_APP_API_URL, // ✅ dynamic for local + Render
});

export default api;
