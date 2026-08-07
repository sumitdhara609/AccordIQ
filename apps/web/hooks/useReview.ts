"use client";

import {
  useCallback,
  useEffect,
  useState,
} from "react";

import { reviewService } from "@/services/review.service";

import type {
  ReviewDocument,
} from "@/types/review";

export function useReview(
  documentId: string
) {

  const [
    review,
    setReview,
  ] =
    useState<ReviewDocument | null>(
      null
    );

  const [
    loading,
    setLoading,
  ] = useState(true);

  const [
    error,
    setError,
  ] =
    useState<string | null>(null);

  const load =
    useCallback(async () => {

      try {

        setLoading(true);

        const data =
          await reviewService.getReview(
            documentId
          );

        setReview(data);

      } catch {

        setError(
          "Unable to load review."
        );

      } finally {

        setLoading(false);

      }

    }, [documentId]);

  useEffect(() => {

    void load();

  }, [load]);

  return {

    review,

    loading,

    error,

    refresh: load,

  };

}