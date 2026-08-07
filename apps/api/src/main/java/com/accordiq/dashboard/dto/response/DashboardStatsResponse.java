package com.accordiq.dashboard.dto.response;

public record DashboardStatsResponse(

        long totalDocuments,

        long uploadedToday,

        long processing,

        long analyzed,

        long reviewPending,

        long failed

) {
}