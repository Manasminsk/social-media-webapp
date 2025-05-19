import axios from "axios";
const API_URL = "http://localhost:8080/posts";

export const getPosts = () => axios.get(API_URL);
export const createPost = (data) => axios.post(API_URL, data);
