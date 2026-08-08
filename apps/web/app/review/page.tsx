"use client";

import { useMemo } from "react";

import { ExportMenu } from "@/components/export/ExportMenu";
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
   * The review route will be connected to the
   * selected document in the next integration step.
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

      <div className="flex flex-wrap items-center justify-between gap-4">
        <div>
          <h2 className="text-xl font-semibold text-gray-900">
            Extracted Data
          </h2>

          <p className="mt-1 text-sm text-gray-500">
            Verify the AI-generated fields before approval.
          </p>
        </div>

        <ExportMenu
          document={{
            id: review.documentId,
            fileName: review.documentName,
            status: review.status,
            fields: review.fields.map(
              (field) => ({
                name: field.name,
                value: field.value,
                confidence: field.confidence,
              })
            ),
          }}
        />
      </div>

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