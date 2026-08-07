"use client";

interface Props {
  onApprove: () => void;
  onReject: () => void;
}

export function ReviewActions({
  onApprove,
  onReject,
}: Props) {
  return (
    <div className="flex gap-4">

      <button
        onClick={onApprove}
        className="
          flex-1
          rounded-xl
          bg-emerald-600
          px-5
          py-3
          font-semibold
          text-white
          hover:bg-emerald-700
        "
      >
        Approve
      </button>

      <button
        onClick={onReject}
        className="
          flex-1
          rounded-xl
          bg-red-600
          px-5
          py-3
          font-semibold
          text-white
          hover:bg-red-700
        "
      >
        Reject
      </button>

    </div>
  );
}