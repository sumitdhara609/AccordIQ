"use client";

import { useCallback, useEffect, useMemo, useState } from "react";

import { documentApi } from "@/lib/api/documents";

import type {
  DocumentResponse,
  DocumentStatus,
} from "@/types/document";

export function useDocuments() {
  const [documents, setDocuments] = useState<DocumentResponse[]>([]);

  const [search, setSearch] = useState("");

  const [status, setStatus] =
    useState<DocumentStatus | "ALL">("ALL");

  const [isLoading, setIsLoading] =
    useState(true);

  const [error, setError] =
    useState<string | null>(null);

  const fetchDocuments = useCallback(async () => {
    try {
      setIsLoading(true);
      setError(null);

      const response =
        await documentApi.getAll();

      setDocuments(response.data);

    } catch (err) {

      console.error(err);

      setError(
        "Failed to load documents."
      );

    } finally {

      setIsLoading(false);

    }

  }, []);

  useEffect(() => {

    void fetchDocuments();

  }, [fetchDocuments]);

  const filteredDocuments = useMemo(() => {

    return documents.filter((document) => {

      const matchesSearch =
        document.originalFileName
          .toLowerCase()
          .includes(
            search.toLowerCase()
          );

      const matchesStatus =
        status === "ALL"
          ? true
          : document.status === status;

      return (
        matchesSearch &&
        matchesStatus
      );

    });

  }, [
    documents,
    search,
    status,
  ]);

  return {

    documents: filteredDocuments,

    allDocuments: documents,

    isLoading,

    error,

    search,

    setSearch,

    status,

    setStatus,

    refresh: fetchDocuments,

  };

}