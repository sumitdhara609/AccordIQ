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
  const isValid = useMemo(() => {
    if (!url.trim()) return true;

    try {
      new URL(url);
      return true;
    } catch {
      return false;
    }
  }, [url]);

  return (
    <div className="space-y-4">
      <input
        type="url"
        value={url}
        onChange={(e) => setUrl(e.target.value)}
        placeholder="https://example.com"
        className="w-full rounded-xl border p-4 outline-none focus:ring-2 focus:ring-black"
      />

      {!isValid && (
        <p className="text-sm text-red-500">
          Please enter a valid URL.
        </p>
      )}
    </div>
  );
}