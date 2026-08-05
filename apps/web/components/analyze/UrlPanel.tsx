"use client";

import { useMemo } from "react";

interface UrlPanelProps {
  url: string;
  setUrl: (url: string) => void;
}

export default function UrlPanel({
  url,
  setUrl,
}: UrlPanelProps) {
  const validation = useMemo(() => {
    if (!url.trim()) {
      return {
        valid: true,
        message: "",
      };
    }

    try {
      const parsed = new URL(url);

      if (!["http:", "https:"].includes(parsed.protocol)) {
        return {
          valid: false,
          message: "Only HTTP and HTTPS URLs are supported.",
        };
      }

      return {
        valid: true,
        message: "Looks good.",
      };
    } catch {
      return {
        valid: false,
        message: "Please enter a valid URL.",
      };
    }
  }, [url]);

  return (
    <div className="space-y-4">
      <input
        type="url"
        value={url}
        onChange={(e) => setUrl(e.target.value)}
        placeholder="https://example.com/article"
        className={`w-full rounded-2xl border p-5 outline-none transition ${
          validation.valid
            ? "border-gray-300 focus:border-black"
            : "border-red-500 focus:border-red-500"
        }`}
      />

      <div className="flex items-center justify-between text-sm">
        <span
          className={
            validation.valid
              ? "text-green-600"
              : "text-red-500"
          }
        >
          {validation.message}
        </span>

        {url && (
          <button
            type="button"
            onClick={() => setUrl("")}
            className="rounded-lg border px-3 py-1 hover:bg-gray-100"
          >
            Clear
          </button>
        )}
      </div>
    </div>
  );
}