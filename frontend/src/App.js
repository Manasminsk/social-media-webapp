// // src/App.js
// import React from 'react';
// import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
// import Home from './pages/Home';
// import Blogs from './pages/Blogs';
// import Posts from './pages/Posts';
// import Activity from './pages/Activity';

// function App() {
//   return (
//     <Router>
//       <nav>
//         <Link to="/">Home</Link> | 
//         <Link to="/blogs">Blogs</Link> | 
//         <Link to="/posts">Posts</Link> | 
//         <Link to="/activity">Activity</Link>
//       </nav>
//       <Routes>
//         <Route path="/" element={<Home />} />
//         <Route path="/blogs" element={<Blogs />} />
//         <Route path="/posts" element={<Posts />} />
//         <Route path="/activity" element={<Activity />} />
//       </Routes>
//     </Router>
//   );
// }

// export default App;

import React from "react";
import Home from "./pages/home";

function App() {
  return (
    <div>
      <Home />
    </div>
  );
}

export default App;
