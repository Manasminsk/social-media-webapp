import React, { useState, useEffect } from "react";
import Navbar from "../components/Navbar";
import CreatePost from "../components/CreatePost";
import UserCard from "../components/UserCard";
import axios from "../api";

export default function Home() {
  const [followers, setFollowers] = useState([]);
  const [following, setFollowing] = useState([]);
  const [suggestions, setSuggestions] = useState([]);
  const [posts, setPosts] = useState([]);

  const loadAll = () => {
    axios.get("/users/me/followers").then(r => setFollowers(r.data));
    axios.get("/users/me/following").then(r => setFollowing(r.data));
    axios.get("/users/suggestions").then(r => setSuggestions(r.data));
    axios.get("/posts").then(r => setPosts(r.data));
  };

  useEffect(loadAll, []);

  return (
    <div className="min-h-screen bg-gray-50">
      <Navbar />
      <div className="container mx-auto p-4 grid grid-cols-1 md:grid-cols-3 gap-6">
        {/* Left Column: Followers & Following */}
        <div className="space-y-6">
          <section className="bg-white p-4 rounded shadow">
            <h2 className="font-semibold mb-2">Followers</h2>
            {followers.map(u => <UserCard key={u.id} user={u} />)}
          </section>
          <section className="bg-white p-4 rounded shadow">
            <h2 className="font-semibold mb-2">Following</h2>
            {following.map(u => <UserCard key={u.id} user={u} />)}
          </section>
        </div>

        {/* Center Column: Feed & Create Post */}
        <div className="space-y-6">
          <CreatePost onCreated={loadAll} />
          <section className="space-y-4">
            {posts.map(post => (
              <div key={post.id} className="bg-white p-4 rounded shadow">
                <div className="font-bold">{post.authorFullName}</div>
                <div>{post.content}</div>
              </div>
            ))}
          </section>
        </div>

        {/* Right Column: Suggestions */}
        <div className="bg-white p-4 rounded shadow">
          <h2 className="font-semibold mb-2">Suggestions</h2>
          {suggestions.map(u => (
            <div key={u.id} className="flex justify-between items-center my-2">
              <span>{u.fullName}</span>
              <button
                className="bg-blue-500 text-white px-3 py-1 rounded"
                onClick={async () => {
                  await axios.post(`/follow/${u.id}`);
                  loadAll();
                }}
              >
                Follow
              </button>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
