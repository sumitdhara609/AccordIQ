"use client";

import { FileText, UploadCloud } from "lucide-react";

import { Button } from "@/components/ui/button";
import type { AnalyzeMode } from "@/types/analyze";

interface AnalyzeTabsProps {
  mode: AnalyzeMode;
  setMode: (mode: AnalyzeMode) => void;
}

export default function AnalyzeTabs({
  mode,
  setMode,
}: AnalyzeTabsProps) {
  return (
    <div className="flex justify-center">
      <div className="inline-flex rounded-2xl border border-border bg-muted p-1">

        <Button
          type="button"
          variant={mode === "upload" ? "default" : "ghost"}
          className="min-w-[180px] rounded-xl"
          onClick={() => setMode("upload")}
        >
          <UploadCloud className="mr-2 h-4 w-4" />
          Upload
        </Button>

        <Button
          type="button"
          variant={mode === "text" ? "default" : "ghost"}
          className="min-w-[180px] rounded-xl"
          onClick={() => setMode("text")}
        >
          <FileText className="mr-2 h-4 w-4" />
          Paste Text
        </Button>

      </div>
    </div>
  );
}