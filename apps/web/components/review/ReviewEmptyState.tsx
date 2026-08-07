"use client";

export function ReviewEmptyState() {
  return (
    <div className="rounded-2xl border border-dashed border-gray-300 bg-white p-10 text-center">
      <h2 className="text-xl font-semibold">
        No review available
      </h2>

      <p className="mt-2 text-gray-500">
        Upload and analyze a document to start the review process.
      </p>
    </div>
  );
}