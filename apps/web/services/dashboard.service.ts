import axios from "axios";

import type {
  DashboardStats,
  RecentDocument,
} from "@/types/dashboard";

const API =
  process.env.NEXT_PUBLIC_API_BASE_URL ??
  "http://localhost:8080/api/v1";

class DashboardService {

  async getStatistics(): Promise<DashboardStats> {

    const response = await axios.get(
      `${API}/dashboard/stats`
    );

    return response.data.data;

  }

  async getRecentDocuments(): Promise<
    RecentDocument[]
  > {

    const response = await axios.get(
      `${API}/dashboard/recent`
    );

    return response.data.data;

  }

}

export default new DashboardService();