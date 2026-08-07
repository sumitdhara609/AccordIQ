package com.accordiq.dashboard.controller;

import com.accordiq.common.response.ApiResponse;
import com.accordiq.dashboard.dto.response.DashboardStatsResponse;
import com.accordiq.dashboard.dto.response.RecentDocumentResponse;
import com.accordiq.dashboard.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(
            DashboardService dashboardService
    ) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<DashboardStatsResponse>>
    getStatistics() {

        return ResponseEntity.ok(

                new ApiResponse<>(

                        true,

                        "Dashboard statistics retrieved successfully.",

                        dashboardService.getStatistics()

                )

        );

    }

    @GetMapping("/recent")
    public ResponseEntity<ApiResponse<List<RecentDocumentResponse>>>
    getRecentDocuments() {

        return ResponseEntity.ok(

                new ApiResponse<>(

                        true,

                        "Recent documents retrieved successfully.",

                        dashboardService.getRecentDocuments()

                )

        );

    }

}