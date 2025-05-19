const PostCard = ({ post }) => (
  <div>
    <h3>{post.user?.username || "Anonymous"}</h3>
    <p>{post.content}</p>
    <small>{post.timestamp}</small>
    <hr />
  </div>
);

export default PostCard;
