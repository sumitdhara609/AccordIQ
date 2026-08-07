"use client";

import {
  CheckCircle2,
  Clock3,
  FileText,
  TriangleAlert,
  XCircle,
} from "lucide-react";

import type { DocumentResponse } from "@/types/document";

interface DocumentStatsProps {
  documents: DocumentResponse[];
}

export function DocumentStats({
  documents,
}: DocumentStatsProps) {
  const total = documents.length;

  const completed = documents.filter(
    (d) => d.status === "COMPLETED"
  ).length;

  const processing = documents.filter(
    (d) => d.status === "PROCESSING"
  ).length;

  const review = documents.filter(
    (d) => d.status === "REVIEW_REQUIRED"
  ).length;

  const failed = documents.filter(
    (d) => d.status === "FAILED"
  ).length;

  const cards = [
    {
      title: "Total",
      value: total,
      icon: <FileText className="h-5 w-5" />,
    },
    {
      title: "Completed",
      value: completed,
      icon: <CheckCircle2 className="h-5 w-5" />,
    },
    {
      title: "Processing",
      value: processing,
      icon: <Clock3 className="h-5 w-5" />,
    },
    {
      title: "Review",
      value: review,
      icon: <TriangleAlert className="h-5 w-5" />,
    },
    {
      title: "Failed",
      value: failed,
      icon: <XCircle className="h-5 w-5" />,
    },
  ];

  return (
    <section className="grid gap-4 sm:grid-cols-2 xl:grid-cols-5">
      {cards.map((card) => (
        <div
          key={card.title}
          className="
            rounded-2xl
            border
            border-gray-200
            bg-white
            p-5
            shadow-sm
          "
        >
          <div className="flex items-center justify-between">
            <div className="rounded-xl bg-gray-100 p-2">
              {card.icon}
            </div>

            <span className="text-3xl font-bold">
              {card.value}
            </span>
          </div>

          <p className="mt-4 text-sm text-gray-500">
            {card.title}
          </p>
        </div>
      ))}
    </section>
  );
}