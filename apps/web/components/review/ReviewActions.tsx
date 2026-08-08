"use client";

import { useState } from "react";

interface Props {
  onApprove: (
    comments: string
  ) => Promise<unknown>;

  onReject: (
    comments: string
  ) => Promise<unknown>;
}

export function ReviewActions({
  onApprove,
  onReject,
}: Props) {
  const [comments, setComments] =
    useState("");

  const [loading, setLoading] =
    useState(false);

  const [action, setAction] =
    useState<"APPROVE" | "REJECT" | null>(
      null
    );

  const canSubmit =
    comments.trim().length > 0 &&
    !loading;

  const handleAction = async (
    selectedAction: "APPROVE" | "REJECT"
  ) => {
    if (!canSubmit) {
      return;
    }

    try {
      setLoading(true);
      setAction(selectedAction);

      if (selectedAction === "APPROVE") {
        await onApprove(comments.trim());
      } else {
        await onReject(comments.trim());
      }

      setComments("");
    } finally {
      setLoading(false);
      setAction(null);
    }
  };

  return (
    <section className="rounded-2xl border border-gray-200 bg-white p-6 shadow-sm">
      <h2 className="text-lg font-semibold text-gray-900">
        Review Decision
      </h2>

      <p className="mt-1 text-sm text-gray-500">
        Add reviewer comments before approving or rejecting this document.
      </p>

      <textarea
        value={comments}
        onChange={(event) =>
          setComments(event.target.value)
        }
        rows={4}
        placeholder="Enter your review comments..."
        className="
          mt-4
          w-full
          resize-none
          rounded-xl
          border
          border-gray-200
          px-4
          py-3
          text-sm
          text-gray-900
          outline-none
          transition
          placeholder:text-gray-400
          focus:border-gray-900
          focus:ring-4
          focus:ring-gray-900/10
        "
      />

      <div className="mt-5 flex flex-wrap gap-3">
        <button
          type="button"
          disabled={!canSubmit}
          onClick={() =>
            void handleAction("APPROVE")
          }
          className="
            rounded-xl
            bg-emerald-600
            px-5
            py-3
            text-sm
            font-semibold
            text-white
            transition
            hover:bg-emerald-700
            disabled:cursor-not-allowed
            disabled:opacity-40
          "
        >
          {loading && action === "APPROVE"
            ? "Approving..."
            : "Approve Document"}
        </button>

        <button
          type="button"
          disabled={!canSubmit}
          onClick={() =>
            void handleAction("REJECT")
          }
          className="
            rounded-xl
            bg-red-600
            px-5
            py-3
            text-sm
            font-semibold
            text-white
            transition
            hover:bg-red-700
            disabled:cursor-not-allowed
            disabled:opacity-40
          "
        >
          {loading && action === "REJECT"
            ? "Rejecting..."
            : "Reject Document"}
        </button>
      </div>
    </section>
  );
}