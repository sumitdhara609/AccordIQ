"use client";

import { useMemo, useState } from "react";
import type { AnalyzeMode } from "@/types/analyze";

export default function useAnalyze() {
  const [mode, setMode] = useState<AnalyzeMode>("upload");

  const [file, setFile] = useState<File | null>(null);
  const [text, setText] = useState("");
  const [url, setUrl] = useState("");

  const [loading, setLoading] = useState(false);

  const canAnalyze = useMemo(() => {
    switch (mode) {
      case "upload":
        return file !== null;

      case "text":
        return text.trim().length > 0;

      case "url":
        return url.trim().length > 0;

      default:
        return false;
    }
  }, [mode, file, text, url]);

  function reset() {
    setFile(null);
    setText("");
    setUrl("");
    setLoading(false);
    setMode("upload");
  }

  return {
    mode,
    setMode,

    file,
    setFile,

    text,
    setText,

    url,
    setUrl,

    loading,
    setLoading,

    canAnalyze,

    reset,
  };
}