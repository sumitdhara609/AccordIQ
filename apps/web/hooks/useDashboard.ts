"use client";

import { useEffect, useState } from "react";

import dashboardService from "@/services/dashboard.service";

import type {
  DashboardStats,
  RecentDocument,
} from "@/types/dashboard";

export default function useDashboard() {

  const [loading, setLoading] =
    useState(true);

  const [stats, setStats] =
    useState<DashboardStats | null>(null);

  const [recentDocuments, setRecentDocuments] =
    useState<RecentDocument[]>([]);

  async function loadDashboard() {

    try {

      const [
        dashboardStats,
        recent
      ] = await Promise.all([

        dashboardService.getStatistics(),

        dashboardService.getRecentDocuments(),

      ]);

      setStats(dashboardStats);

      setRecentDocuments(recent);

    } finally {

      setLoading(false);

    }

  }

  useEffect(() => {

    loadDashboard();

  }, []);

  return {

    loading,

    stats,

    recentDocuments,

    refresh: loadDashboard,

  };

}