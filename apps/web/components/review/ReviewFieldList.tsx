"use client";

import type {
  ReviewField,
} from "@/types/review";

import { ReviewField as Card } from "./ReviewField";

interface Props {
  fields: ReviewField[];
}

export function ReviewFieldList({
  fields,
}: Props) {
  return (
    <div className="space-y-4">
      {fields.map((field) => (
        <Card
          key={field.id}
          field={field}
        />
      ))}
    </div>
  );
}