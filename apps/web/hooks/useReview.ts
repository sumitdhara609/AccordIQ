"use client";

import {
  useCallback,
  useEffect,
  useState,
} from "react";

import { reviewService } from "@/services/review.service";

import type { DocumentResponse } from "@/types/document";
import type {
  DocumentAnalysisDetail,
  ReviewResponse,
} from "@/types/review";

export function useReview(documentId: string) {
  const [analysis, setAnalysis] =
    useState<DocumentAnalysisDetail | null>(null);

  const [review, setReview] =
    useState<ReviewResponse | null>(null);

  const [document, setDocument] =
    useState<DocumentResponse | null>(null);

  const [loading, setLoading] =
    useState(true);

  const [error, setError] =
    useState<string | null>(null);

  const load = useCallback(async () => {
    try {
      setLoading(true);
      setError(null);

      const [
        analysisResponse,
        documentResponse,
      ] = await Promise.all([
        reviewService.getAnalysis(documentId),
        reviewService.getDocument(documentId),
      ]);

      setAnalysis(analysisResponse);
      setDocument(documentResponse);

      try {
        const reviewResponse =
          await reviewService.getReview(documentId);

        setReview(reviewResponse);
      } catch {
        // Reviews are created lazily when
        // a document is approved or rejected.
        setReview({
          id: "",
          documentId,
          status: "PENDING",
          reviewerComments: null,
        });
      }
    } catch (err) {
      console.error(err);

      setError(
        err instanceof Error
          ? err.message
          : "Unable to load document review."
      );
    } finally {
      setLoading(false);
    }
  }, [documentId]);

  useEffect(() => {
    void load();
  }, [load]);

  const updateField = useCallback(
    async (
      fieldId: string,
      value: string
    ) => {
      await reviewService.updateField(
        fieldId,
        value
      );

      setAnalysis((current) => {
        if (!current) {
          return current;
        }

        return {
          ...current,
          fields: current.fields.map(
            (field) =>
              field.id === fieldId
                ? {
                    ...field,
                    value,
                  }
                : field
          ),
        };
      });
    },
    []
  );

  const approve = useCallback(
    async (comments: string) => {
      const response =
        await reviewService.approve(
          documentId,
          comments
        );

      setReview(response);

      return response;
    },
    [documentId]
  );

  const reject = useCallback(
    async (comments: string) => {
      const response =
        await reviewService.reject(
          documentId,
          comments
        );

      setReview(response);

      return response;
    },
    [documentId]
  );

  return {
    analysis,
    review,
    document,
    loading,
    error,
    updateField,
    approve,
    reject,
    refresh: load,
  };
}