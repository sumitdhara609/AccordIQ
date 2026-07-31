"use client";

import type { DocumentStatus } from "@/types/document";

interface DocumentStatusBadgeProps {
  status: DocumentStatus | string;
}

const statusStyles: Record<string, string> = {
  UPLOADED:
    "bg-blue-50 text-blue-700 ring-1 ring-inset ring-blue-200",

  PROCESSING:
    "bg-amber-50 text-amber-700 ring-1 ring-inset ring-amber-200",

  PROCESSED:
    "bg-emerald-50 text-emerald-700 ring-1 ring-inset ring-emerald-200",

  OCR_COMPLETED:
    "bg-emerald-50 text-emerald-700 ring-1 ring-inset ring-emerald-200",

  FAILED:
    "bg-red-50 text-red-700 ring-1 ring-inset ring-red-200",
};

function formatStatus(status: string): string {
  return status
    .toLowerCase()
    .split("_")
    .map(
      (word) => word.charAt(0).toUpperCase() + word.slice(1)
    )
    .join(" ");
}

export function DocumentStatusBadge({
  status,
}: DocumentStatusBadgeProps) {
  const badgeStyle =
    statusStyles[status] ??
    "bg-gray-100 text-gray-700 ring-1 ring-inset ring-gray-200";

  return (
    <span
      className={`inline-flex items-center rounded-full px-3 py-1 text-xs font-semibold tracking-wide ${badgeStyle}`}
    >
      {formatStatus(status)}
    </span>
  );
}