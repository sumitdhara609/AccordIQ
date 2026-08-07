"use client";

import {
  Activity,
  CheckCircle2,
  Clock3,
  FileText,
  TriangleAlert,
  XCircle,
} from "lucide-react";

import useDashboard from "@/hooks/useDashboard";

import { EmptyState } from "@/components/ui/empty-state";
import { Loading } from "@/components/ui/loading";
import { MetricCard } from "@/components/ui/metric-card";
import { PageHeader } from "@/components/ui/page-header";

import QuickActions from "./quick-actions";
import RecentDocuments from "./recent-documents";

export default function DashboardShell() {
  const {
    loading,
    stats,
    recentDocuments,
  } = useDashboard();

  if (loading) {
    return (
      <div className="mx-auto max-w-7xl px-6 py-10">
        <Loading />
      </div>
    );
  }

  if (!stats) {
    return (
      <div className="mx-auto max-w-7xl px-6 py-10">
        <EmptyState
          icon={<Activity className="h-8 w-8" />}
          title="Unable to load dashboard"
          description="Dashboard statistics could not be loaded. Please try again later."
        />
      </div>
    );
  }

  return (
    <div className="mx-auto max-w-7xl space-y-12 px-6 py-10">

      <PageHeader
        title="Dashboard"
        description="Overview of your AI-powered document intelligence platform."
      />

      <section className="grid gap-6 md:grid-cols-2 xl:grid-cols-3">

        <MetricCard
          icon={<FileText className="h-6 w-6" />}
          label="Total Documents"
          value={stats.totalDocuments}
          subtitle="All uploaded documents"
        />

        <MetricCard
          icon={<Clock3 className="h-6 w-6" />}
          label="Uploaded Today"
          value={stats.uploadedToday}
          subtitle="Documents received today"
        />

        <MetricCard
          icon={<Activity className="h-6 w-6" />}
          label="Processing"
          value={stats.processing}
          subtitle="Currently being analyzed"
        />

        <MetricCard
          icon={<CheckCircle2 className="h-6 w-6" />}
          label="Completed"
          value={stats.completed}
          subtitle="Successfully processed"
        />

        <MetricCard
          icon={<TriangleAlert className="h-6 w-6" />}
          label="Review Required"
          value={stats.reviewRequired}
          subtitle="Awaiting manual review"
        />

        <MetricCard
          icon={<XCircle className="h-6 w-6" />}
          label="Failed"
          value={stats.failed}
          subtitle="Processing failed"
        />

      </section>

      <QuickActions />

      <RecentDocuments
        documents={recentDocuments}
      />

    </div>
  );
}