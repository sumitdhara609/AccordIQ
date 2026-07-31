"use client";

import Link from "next/link";

import type { DocumentResponse } from "@/types/document";

import { DocumentStatusBadge } from "./DocumentStatusBadge";

interface DocumentCardProps {
  document: DocumentResponse;
}

function formatFileSize(bytes: number) {
  if (bytes < 1024) {
    return `${bytes} B`;
  }

  const kb = bytes / 1024;

  if (kb < 1024) {
    return `${kb.toFixed(1)} KB`;
  }

  return `${(kb / 1024).toFixed(1)} MB`;
}

function getFileExtension(contentType: string): string {
  const mimeTypes: Record<string, string> = {
    "application/pdf": "PDF",
    "image/png": "PNG",
    "image/jpeg": "JPG",
    "image/jpg": "JPG",
    "image/webp": "WEBP",
  };

  return mimeTypes[contentType] ?? "FILE";
}

export function DocumentCard({ document }: DocumentCardProps) {
  return (
    <Link
      href={`/documents/${document.id}`}
      aria-label={`View ${document.originalFileName}`}
      className="
        group
        block
        rounded-2xl
        border
        border-gray-200
        bg-white
        p-6
        shadow-sm
        transition-all
        duration-200
        hover:-translate-y-1
        hover:border-gray-300
        hover:shadow-lg
      "
    >
      <div className="flex items-start justify-between gap-4">
        <div className="flex min-w-0 items-start gap-4">
          <div
            className="
              flex
              h-12
              w-12
              shrink-0
              items-center
              justify-center
              rounded-xl
              border
              border-gray-200
              bg-gray-50
              text-sm
              font-semibold
              text-gray-700
            "
          >
            {getFileExtension(document.contentType)}
          </div>

          <div className="min-w-0">
            <h3
              className="
                truncate
                text-lg
                font-semibold
                text-gray-900
                transition-colors
                group-hover:text-blue-600
              "
            >
              {document.originalFileName}
            </h3>

            <p className="mt-1 truncate text-sm text-gray-500">
              {document.contentType}
            </p>
          </div>
        </div>

        <DocumentStatusBadge status={document.status} />
      </div>

      <div className="my-6 border-t border-gray-100" />

      <div className="flex items-center justify-between">
        <div>
          <p className="text-xs font-medium uppercase tracking-wide text-gray-400">
            File Size
          </p>

          <p className="mt-1 text-sm font-medium text-gray-900">
            {formatFileSize(document.fileSize)}
          </p>
        </div>

        <div className="text-right">
          <p className="text-xs font-medium uppercase tracking-wide text-gray-400">
            Type
          </p>

          <p className="mt-1 text-sm font-medium text-gray-900">
            {getFileExtension(document.contentType)}
          </p>
        </div>
      </div>
    </Link>
  );
}