"use client";

import { useMemo } from "react";

import { ReviewActions } from "@/components/review/ReviewActions";
import { ReviewEmptyState } from "@/components/review/ReviewEmptyState";
import { ReviewFieldList } from "@/components/review/ReviewFieldList";
import { ReviewHeader } from "@/components/review/ReviewHeader";
import { ReviewLoading } from "@/components/review/ReviewLoading";
import { useReview } from "@/hooks/useReview";

export default function ReviewPage() {

  /*
   * Temporary document id.
   *
   * Phase 2:
   * This page will receive the document id
   * from the router.
   */
  const documentId = "demo";

  const {
    review,
    loading,
    error,
  } = useReview(documentId);

  const documentName = useMemo(() => {
    return review?.documentName ?? "Unknown Document";
  }, [review]);

  if (loading) {
    return (
      <main className="mx-auto max-w-5xl px-6 py-10">
        <ReviewLoading />
      </main>
    );
  }

  if (error || !review) {
    return (
      <main className="mx-auto max-w-5xl px-6 py-10">
        <ReviewEmptyState />
      </main>
    );
  }

  return (
    <main className="mx-auto max-w-5xl space-y-8 px-6 py-10">

      <ReviewHeader
        documentName={documentName}
      />

      <ReviewFieldList
        fields={review.fields}
      />

      <ReviewActions
        onApprove={() => {
          console.log("Approve");
        }}
        onReject={() => {
          console.log("Reject");
        }}
      />

    </main>
  );
}