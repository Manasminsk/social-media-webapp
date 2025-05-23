// src/components/UserCard.jsx
export default function UserCard({ user }) {
  return <div className="p-2 border rounded">{user.fullName}</div>;
}
