
// src/components/CreatePost.jsx
import React, { useState } from "react";
import axios from "../api";

export default function CreatePost({ onCreated }) {
  const [text, setText] = useState("");
  const submit = async e => {
    e.preventDefault();
    if (!text) return;
    await axios.post("/posts", { content: text });
    setText("");
    onCreated();
  };
  return (
    <form onSubmit={submit} className="bg-white p-4 rounded shadow">
      <textarea
        rows={3}
        className="w-full p-2 border rounded"
        placeholder="Make a post..."
        value={text}
        onChange={e=>setText(e.target.value)}
      />
      <button className="mt-2 bg-green-500 text-white px-4 py-2 rounded">
        Post
      </button>
    </form>
  );
}
