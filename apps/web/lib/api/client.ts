import axios from "axios";

const apiClient = axios.create({
  baseURL: process.env.NEXT_PUBLIC_API_BASE_URL,
  timeout: 30000,

  headers: {
    "Content-Type": "application/json",
  },
});

apiClient.interceptors.request.use((config) => {
  // JWT goes here later

  return config;
});

apiClient.interceptors.response.use(
  (response) => response,

  (error) => {
    if (error.response) {
      console.error(error.response.data);
    } else {
      console.error(error.message);
    }

    return Promise.reject(error);
  }
);

export default apiClient;