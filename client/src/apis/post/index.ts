import type { Post } from "@/types/post";

const POST_URL = () => `https://jsonplaceholder.typicode.com/posts`;

export const usePost = () => {
  let posts: Post[] = [];

  const getPosts = (): Post[] => {
    return posts;
  }

  // 게시글 조회 요청
  const fetchPosts = async () => {
    await fetch(POST_URL())
      .then((response) => response.json())
      .then((data) => {
        posts = data;
      })
      .catch((error) => {
        console.error('fetchPosts 실패 : ', error);
      })
  }

  // 단일 게시글 POST 요청
  const createPost = async (post: Post) => {
    await fetch('https://jsonplaceholder.typicode.com/posts', {
      method: 'POST',
      body: JSON.stringify(post),
      headers: {
        'Content-type': 'application/json; charset=UTF-8',
      },
    })
      .then((response) => response.json())
      .then((json) => console.log(json))
      .catch((error) => {
        console.error('createPost 실패 : ', error);
      })
  }

  return {
    getPosts,
    fetchPosts,
    createPost
  }
}

