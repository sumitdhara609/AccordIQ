import axios from "axios";

export async function healthCheck() {
  const response = await axios.get(
    `${process.env.NEXT_PUBLIC_SERVER_URL}/actuator/health`
  );

  return response.data;
}