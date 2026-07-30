"use client";

import { useCallback, useEffect, useState } from "react";

import { documentApi } from "@/lib/api/documents";

import type { DocumentResponse } from "@/types/document";

export function useDocuments() {
  const [documents, setDocuments] = useState<DocumentResponse[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  const fetchDocuments = useCallback(async () => {
    try {
      setIsLoading(true);
      setError(null);

      const response = await documentApi.getAll();

      setDocuments(response.data);
    } catch (err) {
      console.error(err);

      setError("Failed to load documents.");
    } finally {
      setIsLoading(false);
    }
  }, []);

  useEffect(() => {
    void fetchDocuments();
  }, [fetchDocuments]);

  return {
    documents,
    isLoading,
    error,
    refresh: fetchDocuments,
  };
}