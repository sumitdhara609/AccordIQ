"use client";

import { LoaderCircle } from "lucide-react";

import analyzeService from "@/services/analyze.service";

import { Button } from "@/components/ui/button";

import type {
  AnalyzeMode,
  AnalyzeResponse,
} from "@/types/analyze";

interface AnalyzeState {
  mode: AnalyzeMode;

  file: File | null;

  text: string;

  loading: boolean;

  setLoading: (loading: boolean) => void;

  result: AnalyzeResponse | null;

  setResult: (
    result: AnalyzeResponse | null
  ) => void;

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

    analyze.setResult(null);

    try {

      const result =
        await analyzeService.analyze({

          type: analyze.mode,

          file: analyze.file,

          text: analyze.text,

        });

      analyze.setResult(result);

    } catch (error: any) {

      console.error(error);

      alert(
        error?.response?.data?.message ??
        error?.message ??
        "Analysis failed."
      );

    } finally {

      analyze.setLoading(false);

    }
  }

  return (

    <Button
      variant="premium"
      size="hero"
      disabled={
        !analyze.canAnalyze ||
        analyze.loading
      }
      onClick={handleAnalyze}
    >

      {analyze.loading ? (

        <>

          <LoaderCircle className="mr-2 h-5 w-5 animate-spin" />

          Analyzing...

        </>

      ) : (

        "Analyze Document"

      )}

    </Button>

  );

}