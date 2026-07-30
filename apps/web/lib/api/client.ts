import axios from "axios";

const apiBaseUrl = process.env.NEXT_PUBLIC_API_BASE_URL;

console.log("NEXT_PUBLIC_API_BASE_URL =", apiBaseUrl);

if (!apiBaseUrl) {
  console.warn(
    "NEXT_PUBLIC_API_BASE_URL is not defined. Check apps/web/.env.local and restart the Next.js development server."
  );
}

const apiClient = axios.create({
  baseURL: apiBaseUrl,
  timeout: 30000,
  headers: {
    "Content-Type": "application/json",
  },
});

apiClient.interceptors.request.use(
  (config) => {
    console.log(
      `[API Request] ${config.method?.toUpperCase()} ${config.baseURL}${config.url}`
    );

    // TODO: Attach JWT token here when authentication is implemented.
    // const token = localStorage.getItem("accessToken");
    // if (token) {
    //   config.headers.Authorization = `Bearer ${token}`;
    // }

    return config;
  },
  (error) => Promise.reject(error)
);

apiClient.interceptors.response.use(
  (response) => {
    console.log(
      `[API Response] ${response.status} ${response.config.url}`
    );

    return response;
  },
  (error) => {
    if (error.response) {
      console.error("[API Error]", {
        status: error.response.status,
        url: error.config?.url,
        data: error.response.data,
      });
    } else {
      console.error("[Network Error]", error.message);
    }

    return Promise.reject(error);
  }
);

export default apiClient;