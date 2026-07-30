"use client";

export function DocumentLoading() {
  return (
    <div className="grid gap-6 sm:grid-cols-2 xl:grid-cols-3">
      {Array.from({ length: 6 }).map((_, index) => (
        <div
          key={index}
          className="animate-pulse rounded-xl border border-gray-200 bg-white p-5 shadow-sm"
        >
          <div className="mb-4 h-5 w-3/4 rounded bg-gray-200" />
          <div className="mb-6 h-4 w-1/2 rounded bg-gray-200" />

          <div className="flex justify-between">
            <div className="h-4 w-20 rounded bg-gray-200" />
            <div className="h-4 w-16 rounded bg-gray-200" />
          </div>
        </div>
      ))}
    </div>
  );
}