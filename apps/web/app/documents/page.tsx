"use client";

import { DocumentEmptyState } from "@/components/documents/DocumentEmptyState";
import { DocumentList } from "@/components/documents/DocumentList";
import { DocumentLoading } from "@/components/documents/DocumentLoading";
import { useDocuments } from "@/hooks/use-documents";

export default function DocumentsPage() {
  const {
    documents,
    isLoading,
    error,
  } = useDocuments();

  if (isLoading) {
    return (
      <main className="mx-auto max-w-7xl p-8">
        <DocumentLoading />
      </main>
    );
  }

  if (error) {
    return (
      <main className="mx-auto max-w-7xl p-8">
        <div className="rounded-lg border border-red-200 bg-red-50 p-6 text-red-700">
          {error}
        </div>
      </main>
    );
  }

  return (
    <main className="mx-auto max-w-7xl p-8">
      <div className="mb-8">
        <h1 className="text-3xl font-bold">
          Documents
        </h1>

        <p className="mt-2 text-gray-500">
          Manage all uploaded documents.
        </p>
      </div>

      {documents.length === 0 ? (
        <DocumentEmptyState />
      ) : (
        <DocumentList documents={documents} />
      )}
    </main>
  );
}