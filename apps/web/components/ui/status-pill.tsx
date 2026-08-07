import { cn } from "@/lib/utils";
import type { DocumentStatus } from "@/types/dashboard";

interface StatusPillProps {
  status: DocumentStatus;
}

const statusStyles: Record<DocumentStatus, string> = {
  UPLOADED:
    "bg-slate-100 text-slate-700 border-slate-200 dark:bg-slate-900/40 dark:text-slate-300 dark:border-slate-700",

  PROCESSING:
    "bg-blue-100 text-blue-700 border-blue-200 dark:bg-blue-900/30 dark:text-blue-300 dark:border-blue-700",

  OCR_COMPLETED:
    "bg-cyan-100 text-cyan-700 border-cyan-200 dark:bg-cyan-900/30 dark:text-cyan-300 dark:border-cyan-700",

  REVIEW_REQUIRED:
    "bg-amber-100 text-amber-700 border-amber-200 dark:bg-amber-900/30 dark:text-amber-300 dark:border-amber-700",

  COMPLETED:
    "bg-emerald-100 text-emerald-700 border-emerald-200 dark:bg-emerald-900/30 dark:text-emerald-300 dark:border-emerald-700",

  FAILED:
    "bg-red-100 text-red-700 border-red-200 dark:bg-red-900/30 dark:text-red-300 dark:border-red-700",
};

const labels: Record<DocumentStatus, string> = {
  UPLOADED: "Uploaded",
  PROCESSING: "Processing",
  OCR_COMPLETED: "OCR Completed",
  REVIEW_REQUIRED: "Review Required",
  COMPLETED: "Completed",
  FAILED: "Failed",
};

export function StatusPill({
  status,
}: StatusPillProps) {
  return (
    <span
      className={cn(
        "inline-flex items-center rounded-full border px-3 py-1 text-xs font-semibold tracking-wide transition-colors",
        statusStyles[status]
      )}
    >
      <span className="mr-2 h-2 w-2 rounded-full bg-current opacity-80" />
      {labels[status]}
    </span>
  );
}