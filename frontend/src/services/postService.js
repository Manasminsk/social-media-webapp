import axios from "axios";

// ✅ Use the environment variable
const API_URL = `${process.env.REACT_APP_API_URL}/posts`;

export const getPosts = () => axios.get(API_URL);
export const createPost = (data) => axios.post(API_URL, data);
