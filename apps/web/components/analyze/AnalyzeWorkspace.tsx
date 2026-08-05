"use client";

import useAnalyze from "@/hooks/useAnalyze";
import AnalyzeTabs from "./AnalyzeTabs";
import UploadPanel from "./UploadPanel";
import TextPanel from "./TextPanel";
import UrlPanel from "./UrlPanel";
import AnalyzeButton from "./AnalyzeButton";

export default function AnalyzeWorkspace() {
  const analyze = useAnalyze();

  return (
    <main className="mx-auto flex min-h-screen w-full max-w-5xl flex-col px-6 py-12">
      {/* Hero */}
      <section className="mb-10 text-center">
        <h1 className="text-4xl font-bold tracking-tight">
          Understand Any Document.
          <br />
          In Seconds.
        </h1>

        <p className="mt-4 text-muted-foreground">
          Upload a document, paste text, or analyze a website.
        </p>
      </section>

      {/* Tabs */}
      <AnalyzeTabs
        mode={analyze.mode}
        setMode={analyze.setMode}
      />

      {/* Active Panel */}
      <div className="mt-8">
        {analyze.mode === "upload" && (
          <UploadPanel
            file={analyze.file}
            setFile={analyze.setFile}
          />
        )}

        {analyze.mode === "text" && (
          <TextPanel
            text={analyze.text}
            setText={analyze.setText}
          />
        )}

        {analyze.mode === "url" && (
          <UrlPanel
            url={analyze.url}
            setUrl={analyze.setUrl}
          />
        )}
      </div>

      {/* Analyze Button */}
      <div className="mt-8 self-center">
        <AnalyzeButton analyze={analyze} />
      </div>
    </main>
  );
}