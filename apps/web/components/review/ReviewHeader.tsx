"use client";

interface ReviewHeaderProps {
  documentName: string;
}

export function ReviewHeader({
  documentName,
}: ReviewHeaderProps) {
  return (
    <div className="rounded-2xl border border-gray-200 bg-white p-6 shadow-sm">
      <h1 className="text-3xl font-bold">
        Review Document
      </h1>

      <p className="mt-2 text-gray-500">
        Verify the extracted fields before approving the document.
      </p>

      <div className="mt-5 rounded-xl bg-gray-50 px-4 py-3">
        <p className="text-sm text-gray-500">
          Document
        </p>

        <p className="font-semibold">
          {documentName}
        </p>
      </div>
    </div>
  );
}