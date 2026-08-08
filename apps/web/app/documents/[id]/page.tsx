"use client";

import Link from "next/link";
import { useEffect, useState } from "react";
import { useParams, useRouter } from "next/navigation";

import { documentApi } from "@/lib/api/documents";

import { DocumentStatusBadge } from "@/components/documents/DocumentStatusBadge";

import type { DocumentResponse } from "@/types/document";

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

function getFileExtension(contentType: string) {
  const mimeTypes: Record<string, string> = {
    "application/pdf": "PDF",
    "image/png": "PNG",
    "image/jpeg": "JPG",
    "image/jpg": "JPG",
    "image/webp": "WEBP",
  };

  return mimeTypes[contentType] ?? "FILE";
}

export default function DocumentDetailsPage() {
  const { id } = useParams<{ id: string }>();
  const router = useRouter();

  const [document, setDocument] =
    useState<DocumentResponse | null>(null);

  const [loading, setLoading] = useState(true);
  const [deleting, setDeleting] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    async function load() {
      try {
        const response = await documentApi.getById(id);

        setDocument(response.data);
      } catch {
        setError("Failed to load document.");
      } finally {
        setLoading(false);
      }
    }

    void load();
  }, [id]);

  async function handleDelete() {
    if (!document) {
      return;
    }

    const confirmed = window.confirm(
      `Delete "${document.originalFileName}"?`
    );

    if (!confirmed) {
      return;
    }

    try {
      setDeleting(true);

      await documentApi.delete(document.id);

      router.push("/documents");
    } catch {
      window.alert("Failed to delete document.");
    } finally {
      setDeleting(false);
    }
  }

  if (loading) {
    return (
      <main className="min-h-screen bg-gray-50 px-6 py-12">
        <div className="mx-auto max-w-5xl">
          <div className="rounded-2xl border border-gray-200 bg-white p-8 shadow-sm">
            <div className="h-8 w-64 animate-pulse rounded-lg bg-gray-200" />
            <div className="mt-4 h-4 w-96 animate-pulse rounded bg-gray-100" />
            <div className="mt-10 h-32 animate-pulse rounded-xl bg-gray-100" />
          </div>
        </div>
      </main>
    );
  }

  if (error || !document) {
    return (
      <main className="flex min-h-screen items-center justify-center bg-gray-50 px-6">
        <div className="text-center">
          <h1 className="text-2xl font-semibold text-gray-900">
            {error || "Document not found."}
          </h1>

          <Link
            href="/documents"
            className="mt-6 inline-flex rounded-xl bg-gray-900 px-5 py-3 text-sm font-medium text-white transition hover:bg-gray-800"
          >
            Back to Documents
          </Link>
        </div>
      </main>
    );
  }

  return (
    <main className="min-h-screen bg-gray-50 px-6 py-12">
      <div className="mx-auto max-w-5xl">
        {/* Header */}
        <div className="mb-8 flex flex-col gap-5 sm:flex-row sm:items-start sm:justify-between">
          <div className="min-w-0">
            <Link
              href="/documents"
              className="text-sm font-medium text-gray-500 transition hover:text-gray-900"
            >
              ← Documents
            </Link>

            <h1 className="mt-4 truncate text-3xl font-bold tracking-tight text-gray-900">
              {document.originalFileName}
            </h1>

            <p className="mt-2 text-sm text-gray-500">
              Document details and processing information
            </p>
          </div>

          <DocumentStatusBadge status={document.status} />
        </div>

        {/* Document information */}
        <section className="rounded-2xl border border-gray-200 bg-white p-6 shadow-sm">
          <h2 className="text-lg font-semibold text-gray-900">
            Document Information
          </h2>

          <div className="mt-6 grid gap-6 sm:grid-cols-2 lg:grid-cols-3">
            <div>
              <p className="text-xs font-semibold uppercase tracking-wide text-gray-400">
                File Name
              </p>

              <p className="mt-2 truncate text-sm font-medium text-gray-900">
                {document.originalFileName}
              </p>
            </div>

            <div>
              <p className="text-xs font-semibold uppercase tracking-wide text-gray-400">
                File Type
              </p>

              <p className="mt-2 text-sm font-medium text-gray-900">
                {getFileExtension(document.contentType)}
              </p>
            </div>

            <div>
              <p className="text-xs font-semibold uppercase tracking-wide text-gray-400">
                Content Type
              </p>

              <p className="mt-2 truncate text-sm font-medium text-gray-900">
                {document.contentType}
              </p>
            </div>

            <div>
              <p className="text-xs font-semibold uppercase tracking-wide text-gray-400">
                File Size
              </p>

              <p className="mt-2 text-sm font-medium text-gray-900">
                {formatFileSize(document.fileSize)}
              </p>
            </div>

            <div>
              <p className="text-xs font-semibold uppercase tracking-wide text-gray-400">
                Status
              </p>

              <div className="mt-2">
                <DocumentStatusBadge status={document.status} />
              </div>
            </div>
          </div>
        </section>

        {/* Actions */}
        <section className="mt-6 rounded-2xl border border-gray-200 bg-white p-6 shadow-sm">
          <h2 className="text-lg font-semibold text-gray-900">
            Actions
          </h2>

          <p className="mt-2 text-sm text-gray-500">
            Review extracted information or manage this document.
          </p>

          <div className="mt-6 flex flex-wrap gap-3">
            <Link
              href={`/review/${document.id}`}
              className="inline-flex items-center justify-center rounded-xl bg-gray-900 px-5 py-3 text-sm font-medium text-white transition hover:bg-gray-800"
            >
              Review Document
            </Link>

            <Link
              href="/documents"
              className="inline-flex items-center justify-center rounded-xl border border-gray-200 bg-white px-5 py-3 text-sm font-medium text-gray-700 transition hover:bg-gray-50"
            >
              Back to Documents
            </Link>

            <button
              type="button"
              onClick={handleDelete}
              disabled={deleting}
              className="inline-flex items-center justify-center rounded-xl border border-red-200 px-5 py-3 text-sm font-medium text-red-600 transition hover:bg-red-50 disabled:cursor-not-allowed disabled:opacity-50"
            >
              {deleting ? "Deleting..." : "Delete Document"}
            </button>
          </div>
        </section>
      </div>
    </main>
  );
}