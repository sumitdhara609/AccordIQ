"use client";

import {
  useState,
} from "react";

import type {
  ReviewField as Field,
} from "@/types/review";

interface Props {
  field: Field;
  onSave: (
    fieldId: string,
    value: string
  ) => Promise<void>;
}

export function ReviewField({
  field,
  onSave,
}: Props) {
  const [value, setValue] =
    useState(field.value);

  const [saving, setSaving] =
    useState(false);

  const [saved, setSaved] =
    useState(false);

  const confidenceLabel =
    field.confidence == null
      ? "N/A"
      : `${field.confidence.toFixed(1)}%`;

  const handleSave = async () => {
    if (value === field.value) {
      return;
    }

    try {
      setSaving(true);
      setSaved(false);

      await onSave(
        field.id,
        value
      );

      setSaved(true);

      window.setTimeout(() => {
        setSaved(false);
      }, 2000);
    } finally {
      setSaving(false);
    }
  };

  return (
    <div className="rounded-xl border border-gray-200 bg-white p-5">
      <div className="flex items-center justify-between gap-4">
        <h3 className="font-semibold text-gray-900">
          {field.name}
        </h3>

        <span className="shrink-0 rounded-full bg-blue-50 px-3 py-1 text-xs font-semibold text-blue-700">
          {confidenceLabel}
        </span>
      </div>

      <div className="mt-3 flex gap-3">
        <input
          type="text"
          value={value}
          onChange={(event) =>
            setValue(event.target.value)
          }
          className="
            min-w-0
            flex-1
            rounded-lg
            border
            border-gray-200
            px-3
            py-2
            text-sm
            text-gray-900
            outline-none
            transition
            focus:border-blue-500
            focus:ring-4
            focus:ring-blue-500/10
          "
        />

        <button
          type="button"
          onClick={() => void handleSave()}
          disabled={
            saving ||
            value === field.value
          }
          className="
            rounded-lg
            bg-gray-900
            px-4
            py-2
            text-sm
            font-medium
            text-white
            transition
            hover:bg-gray-800
            disabled:cursor-not-allowed
            disabled:opacity-40
          "
        >
          {saving
            ? "Saving..."
            : saved
              ? "Saved"
              : "Save"}
        </button>
      </div>
    </div>
  );
}