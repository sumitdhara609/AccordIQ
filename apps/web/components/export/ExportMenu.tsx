"use client";

import {
  Download,
  FileSpreadsheet,
  FileJson,
} from "lucide-react";

import {
  exportService,
} from "@/services/export.service";

import type {
  ExportDocument,
} from "@/types/export";

interface ExportMenuProps {
  document: ExportDocument;
}

export function ExportMenu({
  document,
}: ExportMenuProps) {
  return (
    <div className="flex flex-wrap gap-3">
      <button
        type="button"
        onClick={() =>
          exportService.downloadJson(
            document
          )
        }
        className="
          inline-flex
          items-center
          gap-2
          rounded-xl
          border
          border-gray-200
          bg-white
          px-4
          py-2.5
          text-sm
          font-medium
          text-gray-900
          shadow-sm
          transition
          hover:bg-gray-50
        "
      >
        <FileJson className="h-4 w-4" />

        Export JSON
      </button>

      <button
        type="button"
        onClick={() =>
          exportService.downloadCsv(
            document
          )
        }
        className="
          inline-flex
          items-center
          gap-2
          rounded-xl
          bg-gray-900
          px-4
          py-2.5
          text-sm
          font-medium
          text-white
          shadow-sm
          transition
          hover:bg-gray-800
        "
      >
        <FileSpreadsheet className="h-4 w-4" />

        Export CSV
      </button>
    </div>
  );
}