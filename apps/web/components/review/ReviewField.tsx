"use client";

import type {
  ReviewField as Field,
} from "@/types/review";

interface Props {
  field: Field;
}

export function ReviewField({
  field,
}: Props) {
  return (
    <div className="rounded-xl border border-gray-200 bg-white p-5">
      <div className="flex items-center justify-between">

        <h3 className="font-semibold">
          {field.name}
        </h3>

        <span className="rounded-full bg-blue-50 px-3 py-1 text-xs font-semibold text-blue-700">
          {field.confidence.toFixed(1)}%
        </span>

      </div>

      <input
        defaultValue={field.value}
        className="
          mt-3
          w-full
          rounded-lg
          border
          border-gray-200
          px-3
          py-2
          outline-none
          focus:border-blue-500
        "
      />
    </div>
  );
}