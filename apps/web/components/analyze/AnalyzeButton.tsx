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
      const result = await analyzeService.analyze({
        type: analyze.mode,
        file: analyze.file,
        text: analyze.text,
        url: analyze.url,
      });

      console.log(result);

      alert(result.summary);
    } catch (error) {
      console.error(error);
      alert("Unable to analyze the content.");
    } finally {
      analyze.setLoading(false);
    }
  }

  return (
    <button
      type="button"
      onClick={handleAnalyze}
      disabled={!analyze.canAnalyze || analyze.loading}
      className="inline-flex min-w-[220px] items-center justify-center rounded-xl bg-black px-8 py-3 text-base font-semibold text-white transition-all duration-200 hover:scale-[1.02] hover:bg-neutral-800 disabled:cursor-not-allowed disabled:opacity-50 disabled:hover:scale-100"
    >
      {analyze.loading ? (
        <>
          <svg
            className="mr-2 h-5 w-5 animate-spin"
            viewBox="0 0 24 24"
            fill="none"
          >
            <circle
              cx="12"
              cy="12"
              r="10"
              stroke="currentColor"
              strokeWidth="3"
              opacity="0.25"
            />
            <path
              d="M22 12a10 10 0 0 1-10 10"
              stroke="currentColor"
              strokeWidth="3"
            />
          </svg>

          Analyzing...
        </>
      ) : (
        "Analyze Document"
      )}
    </button>
  );
}