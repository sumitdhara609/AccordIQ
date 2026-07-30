"use client";

import Link from "next/link";

import type { DocumentResponse } from "@/types/document";

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

export function DocumentCard({ document }: DocumentCardProps) {
  return (
    <Link
      href={`/documents/${document.id}`}
      className="block rounded-xl border border-gray-200 bg-white p-5 shadow-sm transition-all duration-200 hover:-translate-y-1 hover:shadow-md"
    >
      <div className="flex items-start justify-between">
        <div className="min-w-0">
          <h3 className="truncate text-lg font-semibold text-gray-900">
            {document.originalFileName}
          </h3>

          <p className="mt-1 text-sm text-gray-500">
            {document.contentType}
          </p>
        </div>

        <span
          className="rounded-full bg-blue-100 px-3 py-1 text-xs font-medium text-blue-700"
        >
          {document.status}
        </span>
      </div>

      <div className="mt-5 flex items-center justify-between text-sm text-gray-500">
        <span>{formatFileSize(document.fileSize)}</span>

        <span>{document.contentType}</span>
      </div>
    </Link>
  );
}