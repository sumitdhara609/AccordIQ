"use client";

import { useRef } from "react";
import { FileText, Upload } from "lucide-react";

import { Button } from "@/components/ui/button";
import { useUpload } from "@/hooks/use-upload";

export default function UploadPage() {
  const inputRef = useRef<HTMLInputElement>(null);

  const {
    selectedFile,
    error,
    isDragging,
    setIsDragging,
    selectFile,
  } = useUpload();

  function handleBrowseClick() {
    inputRef.current?.click();
  }

  function handleFileChange(
    event: React.ChangeEvent<HTMLInputElement>
  ) {
    const file = event.target.files?.[0];

    if (!file) return;

    selectFile(file);
  }

  function handleDragOver(
    event: React.DragEvent<HTMLDivElement>
  ) {
    event.preventDefault();
    setIsDragging(true);
  }

  function handleDragLeave() {
    setIsDragging(false);
  }

  function handleDrop(
    event: React.DragEvent<HTMLDivElement>
  ) {
    event.preventDefault();

    setIsDragging(false);

    const file = event.dataTransfer.files?.[0];

    if (!file) return;

    selectFile(file);
  }

  return (
    <main className="min-h-screen bg-background">
      <div className="mx-auto flex max-w-5xl flex-col gap-10 px-6 py-16">
        <div>
          <h1 className="text-4xl font-bold tracking-tight">
            Upload Document
          </h1>

          <p className="mt-3 max-w-2xl text-muted-foreground">
            Upload invoices, receipts, contracts or forms.
            AccordIQ securely prepares them for OCR,
            validation and AI analysis.
          </p>
        </div>

        <section
          onClick={handleBrowseClick}
          onDragOver={handleDragOver}
          onDragLeave={handleDragLeave}
          onDrop={handleDrop}
          className={`cursor-pointer rounded-3xl border border-dashed p-12 transition-all duration-300 ${
            isDragging
              ? "border-primary bg-primary/5"
              : "border-border bg-card hover:border-primary"
          }`}
        >
          <div className="flex flex-col items-center justify-center text-center">
            <div className="mb-6 rounded-full bg-primary/10 p-5">
              <Upload className="h-10 w-10 text-primary" />
            </div>

            <h2 className="text-2xl font-semibold">
              Drag & Drop your document
            </h2>

            <p className="mt-3 text-muted-foreground">
              or click anywhere to browse your computer
            </p>

            <input
              ref={inputRef}
              type="file"
              hidden
              accept=".pdf,.png,.jpg,.jpeg"
              onChange={handleFileChange}
            />

            <Button
              size="lg"
              className="mt-8"
              onClick={(event) => {
                event.stopPropagation();
                handleBrowseClick();
              }}
            >
              Choose File
            </Button>

            <div className="mt-8">
              {selectedFile ? (
                <div className="flex items-center gap-3 rounded-xl border bg-background px-5 py-3 shadow-sm">
                  <FileText className="h-5 w-5 text-primary" />

                  <div className="text-left">
                    <p className="font-medium">
                      {selectedFile.name}
                    </p>

                    <p className="text-sm text-muted-foreground">
                      {(selectedFile.size / 1024 / 1024).toFixed(2)} MB
                    </p>
                  </div>
                </div>
              ) : (
                <p className="text-sm text-muted-foreground">
                  No file selected
                </p>
              )}
            </div>

            {error && (
              <p className="mt-4 text-sm font-medium text-red-500">
                {error}
              </p>
            )}

            <p className="mt-8 text-sm text-muted-foreground">
              Supported formats: PDF, PNG, JPG
            </p>

            <p className="text-sm text-muted-foreground">
              Maximum file size: 10 MB
            </p>
          </div>
        </section>
      </div>
    </main>
  );
}