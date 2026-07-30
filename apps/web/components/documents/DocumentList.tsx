"use client";

import type { DocumentResponse } from "@/types/document";

import { DocumentCard } from "./DocumentCard";

interface DocumentListProps {
  documents: DocumentResponse[];
}

export function DocumentList({ documents }: DocumentListProps) {
  if (documents.length === 0) {
    return (
      <div className="rounded-xl border border-dashed border-gray-300 bg-white p-12 text-center">
        <div className="mx-auto mb-4 flex h-16 w-16 items-center justify-center rounded-full bg-gray-100 text-3xl">
          📄
        </div>

        <h2 className="text-xl font-semibold text-gray-900">
          No documents found
        </h2>

        <p className="mt-2 text-sm text-gray-500">
          Upload your first document to start building your document library.
        </p>
      </div>
    );
  }

  return (
    <div className="grid gap-6 sm:grid-cols-2 xl:grid-cols-3">
      {documents.map((document) => (
        <DocumentCard
          key={document.id}
          document={document}
        />
      ))}
    </div>
  );
}