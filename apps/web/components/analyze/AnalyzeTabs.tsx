"use client";

import type { AnalyzeMode } from "@/types/analyze";

interface AnalyzeTabsProps {
  mode: AnalyzeMode;
  setMode: (mode: AnalyzeMode) => void;
}

const tabs: {
  value: AnalyzeMode;
  label: string;
}[] = [
  {
    value: "upload",
    label: "📄 Upload",
  },
  {
    value: "text",
    label: "📝 Paste Text",
  },
  {
    value: "url",
    label: "🌐 Website",
  },
];

export default function AnalyzeTabs({
  mode,
  setMode,
}: AnalyzeTabsProps) {
  return (
    <div className="flex flex-wrap justify-center gap-3">
      {tabs.map((tab) => (
        <button
          key={tab.value}
          type="button"
          onClick={() => setMode(tab.value)}
          className={`rounded-lg border px-5 py-2 transition ${
            mode === tab.value
              ? "bg-black text-white"
              : "bg-white hover:bg-gray-100"
          }`}
        >
          {tab.label}
        </button>
      ))}
    </div>
  );
}