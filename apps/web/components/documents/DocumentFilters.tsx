"use client";

import type { DocumentStatus } from "@/types/document";

interface DocumentFiltersProps {
  search: string;
  onSearchChange: (value: string) => void;

  status: DocumentStatus | "ALL";
  onStatusChange: (
    value: DocumentStatus | "ALL"
  ) => void;
}

const filters: (DocumentStatus | "ALL")[] = [
  "ALL",
  "UPLOADED",
  "PROCESSING",
  "OCR_COMPLETED",
  "REVIEW_REQUIRED",
  "COMPLETED",
  "FAILED",
];

export function DocumentFilters({
  search,
  onSearchChange,
  status,
  onStatusChange,
}: DocumentFiltersProps) {
  return (
    <div className="space-y-5">
      <input
        value={search}
        onChange={(event) =>
          onSearchChange(event.target.value)
        }
        placeholder="Search documents..."
        className="
          w-full
          rounded-xl
          border
          border-gray-200
          bg-white
          px-4
          py-3
          outline-none
          focus:border-blue-500
        "
      />

      <div className="flex flex-wrap gap-2">
        {filters.map((item) => (
          <button
            key={item}
            onClick={() =>
              onStatusChange(item)
            }
            className={`
              rounded-full
              px-4
              py-2
              text-sm
              transition
              ${
                status === item
                  ? "bg-gray-900 text-white"
                  : "bg-gray-100 hover:bg-gray-200"
              }
            `}
          >
            {item.replaceAll("_", " ")}
          </button>
        ))}
      </div>
    </div>
  );
}