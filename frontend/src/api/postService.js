import { BASE_URL } from '../utils/constants';

const postService = {
  getPostsByQuery: async (query) => {
    const response = await axios.get(`${BASE_URL}/job-posts/${query}`);
    return response;
  },
  getPosts: async () => {
    const response = await axios.get(`${BASE_URL}/job-posts`);
    return response;
  },
  createPost: async (data) => {
    const response = await axios.post(`${BASE_URL}/post`, data);
    return response;
  },
};

export default postService;
