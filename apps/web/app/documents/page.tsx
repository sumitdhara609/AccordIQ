"use client";

import Link from "next/link";
import { useMemo, useState } from "react";

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

  const [search, setSearch] = useState("");

  const filteredDocuments = useMemo(() => {
    return documents.filter((document) =>
      document.originalFileName
        .toLowerCase()
        .includes(search.toLowerCase())
    );
  }, [documents, search]);

  if (isLoading) {
    return (
      <main className="mx-auto max-w-7xl px-6 py-10">
        <DocumentLoading />
      </main>
    );
  }

  if (error) {
    return (
      <main className="mx-auto max-w-7xl px-6 py-10">
        <div className="rounded-xl border border-red-200 bg-red-50 p-6 text-red-700">
          {error}
        </div>
      </main>
    );
  }

  return (
    <main className="mx-auto max-w-7xl px-6 py-10">
      <div className="mb-10 flex flex-col gap-6 lg:flex-row lg:items-center lg:justify-between">
        <div>
          <h1 className="text-4xl font-bold tracking-tight text-gray-900">
            Documents
          </h1>

          <p className="mt-2 text-base text-gray-600">
            Manage and monitor all uploaded documents.
          </p>
        </div>

        <Link
          href="/upload"
          className="
            inline-flex
            items-center
            justify-center
            rounded-xl
            bg-gray-900
            px-5
            py-3
            text-sm
            font-medium
            text-white
            transition-colors
            hover:bg-gray-800
          "
        >
          Upload Document
        </Link>
      </div>

      <div className="mb-8">
        <input
          type="text"
          placeholder="Search documents..."
          value={search}
          onChange={(event) => setSearch(event.target.value)}
          className="
            w-full
            rounded-xl
            border
            border-gray-200
            bg-white
            px-4
            py-3
            text-sm
            text-gray-900
            outline-none
            transition-all
            placeholder:text-gray-400
            focus:border-gray-900
            focus:ring-4
            focus:ring-gray-900/10
          "
        />
      </div>

      {filteredDocuments.length === 0 ? (
        <DocumentEmptyState />
      ) : (
        <DocumentList documents={filteredDocuments} />
      )}
    </main>
  );
}