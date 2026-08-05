"use client";

import { useMemo } from "react";

interface TextPanelProps {
  text: string;
  setText: (text: string) => void;
}

export default function TextPanel({
  text,
  setText,
}: TextPanelProps) {
  const wordCount = useMemo(() => {
    const trimmed = text.trim();

    if (!trimmed) {
      return 0;
    }

    return trimmed.split(/\s+/).length;
  }, [text]);

  return (
    <div className="space-y-4">
      <textarea
        value={text}
        onChange={(e) => setText(e.target.value)}
        placeholder="Paste your document, contract, article, report, or any text here..."
        rows={14}
        className="min-h-[350px] w-full rounded-2xl border p-5 outline-none transition focus:border-black"
      />

      <div className="flex flex-wrap items-center justify-between gap-3 text-sm text-gray-500">
        <div className="flex gap-5">
          <span>
            <strong>{text.length}</strong> characters
          </span>

          <span>
            <strong>{wordCount}</strong> words
          </span>
        </div>

        <button
          type="button"
          onClick={() => setText("")}
          disabled={!text}
          className="rounded-lg border px-4 py-2 transition hover:bg-gray-100 disabled:cursor-not-allowed disabled:opacity-50"
        >
          Clear
        </button>
      </div>
    </div>
  );
}