"use client";

import Link from "next/link";
import { useEffect, useState } from "react";
import { useParams, useRouter } from "next/navigation";

import { documentApi } from "@/lib/api/documents";

import type { DocumentResponse } from "@/types/document";

import { DocumentStatusBadge } from "@/components/documents/DocumentStatusBadge";

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

  const [document, setDocument] = useState<DocumentResponse | null>(null);
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
      alert("Failed to delete document.");
    } finally {
      setDeleting(false);
    }
  }

  if (loading) {
    return (
      <main className="mx-auto max-w-4xl px-6 py-10">
        Loading...
      </main>
    );
  }

  if (error || !document) {
    return (
      <main className="mx-auto max-w-4xl px-6 py-10">
        {error || "Document not found."}
      </main>
    );
  }

  return (
    <main className="mx-auto max-w-4xl px-6 py-10">
      <Link
        href="/documents"
        className="text-sm font-medium text-gray-600 hover:text-gray-900"
      >
        ← Back to Documents
      </Link>

      <div className="mt-8 rounded-2xl border border-gray-200 bg-white p-8 shadow-sm">
        <div className="flex items-start justify-between gap-6">
          <div>
            <h1 className="text-3xl font-bold text-gray-900">
              {document.originalFileName}
            </h1>

            <p className="mt-2 text-gray-500">
              {document.contentType}
            </p>
          </div>

          <DocumentStatusBadge status={document.status} />
        </div>

        <div className="mt-10 grid gap-6 md:grid-cols-2">
          <div>
            <p className="text-sm font-medium text-gray-500">
              File Size
            </p>

            <p className="mt-1 text-lg font-semibold text-gray-900">
              {formatFileSize(document.fileSize)}
            </p>
          </div>

          <div>
            <p className="text-sm font-medium text-gray-500">
              Type
            </p>

            <p className="mt-1 text-lg font-semibold text-gray-900">
              {getFileExtension(document.contentType)}
            </p>
          </div>
        </div>

        <div className="mt-12 border-t border-gray-200 pt-8">
          <button
            onClick={handleDelete}
            disabled={deleting}
            className="rounded-xl bg-red-600 px-5 py-3 text-sm font-medium text-white transition hover:bg-red-700 disabled:opacity-50"
          >
            {deleting ? "Deleting..." : "Delete Document"}
          </button>
        </div>
      </div>
    </main>
  );
}