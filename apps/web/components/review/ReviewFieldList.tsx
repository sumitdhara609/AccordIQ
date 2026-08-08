"use client";

import type {
  ReviewField,
} from "@/types/review";

import { ReviewField as FieldCard } from "./ReviewField";

interface Props {
  fields: ReviewField[];
  onSave: (
    fieldId: string,
    value: string
  ) => Promise<void>;
}

export function ReviewFieldList({
  fields,
  onSave,
}: Props) {
  if (fields.length === 0) {
    return (
      <div className="rounded-2xl border border-dashed border-gray-300 bg-white p-10 text-center">
        <h2 className="text-lg font-semibold text-gray-900">
          No extracted fields
        </h2>

        <p className="mt-2 text-sm text-gray-500">
          The AI analysis did not return any structured fields.
        </p>
      </div>
    );
  }

  return (
    <div className="space-y-4">
      {fields.map((field) => (
        <FieldCard
          key={field.id}
          field={field}
          onSave={onSave}
        />
      ))}
    </div>
  );
}