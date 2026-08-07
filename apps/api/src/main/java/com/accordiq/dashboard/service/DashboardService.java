package com.accordiq.dashboard.service;

import com.accordiq.dashboard.dto.response.DashboardStatsResponse;
import com.accordiq.dashboard.dto.response.RecentDocumentResponse;

import java.util.List;

public interface DashboardService {

    DashboardStatsResponse getStatistics();

    List<RecentDocumentResponse> getRecentDocuments();

}