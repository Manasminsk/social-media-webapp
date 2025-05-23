// src/components/Navbar.jsx
import React, { useEffect, useState } from "react";
import axios from "../api";

export default function Navbar() {
  const [me, setMe] = useState({ fullName: "" });
  useEffect(() => {
    axios.get("/users/me").then(r=>setMe(r.data));
  }, []);
  return (
    <nav className="bg-blue-600 text-white p-4 flex justify-between">
      <div className="text-xl font-bold">SocialApp</div>
      <div>Welcome, <strong>{me.fullName}</strong></div>
    </nav>
  );
}
