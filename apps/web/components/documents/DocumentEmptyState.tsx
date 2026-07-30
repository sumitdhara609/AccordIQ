"use client";

import Link from "next/link";

export function DocumentEmptyState() {
  return (
    <div className="rounded-xl border border-dashed border-gray-300 bg-white p-12 text-center">
      <div className="mb-4 text-6xl">📄</div>

      <h2 className="text-2xl font-semibold">
        No documents yet
      </h2>

      <p className="mt-2 text-gray-500">
        Upload your first document to start building your document library.
      </p>

      <Link
        href="/upload"
        className="mt-6 inline-flex rounded-lg bg-blue-600 px-5 py-2 text-white transition hover:bg-blue-700"
      >
        Upload Document
      </Link>
    </div>
  );
}