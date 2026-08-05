"use client";

import analyzeService from "@/services/analyze.service";
import type { AnalyzeMode } from "@/types/analyze";

interface AnalyzeState {
  mode: AnalyzeMode;
  file: File | null;
  text: string;
  url: string;
  loading: boolean;
  setLoading: (loading: boolean) => void;
  canAnalyze: boolean;
}

interface AnalyzeButtonProps {
  analyze: AnalyzeState;
}

export default function AnalyzeButton({
  analyze,
}: AnalyzeButtonProps) {
  async function handleAnalyze() {
    if (!analyze.canAnalyze || analyze.loading) {
      return;
    }

    analyze.setLoading(true);

    try {
      const response = await analyzeService.analyze({
        type: analyze.mode,
        file: analyze.file,
        text: analyze.text,
        url: analyze.url,
      });

      console.log("Analysis Result:", response);

      // We'll replace this with the results page in Sprint 3.
      alert(response.summary);
    } catch (error) {
      console.error(error);
      alert("Analysis failed.");
    } finally {
      analyze.setLoading(false);
    }
  }

  return (
    <button
      type="button"
      onClick={handleAnalyze}
      disabled={!analyze.canAnalyze || analyze.loading}
      className="rounded-xl bg-black px-8 py-3 text-white transition hover:bg-neutral-800 disabled:cursor-not-allowed disabled:opacity-50"
    >
      {analyze.loading ? "Analyzing..." : "Analyze"}
    </button>
  );
}