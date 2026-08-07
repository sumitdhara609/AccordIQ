"use client";

import { useRef } from "react";

import {
  FileText,
  Trash2,
  UploadCloud,
} from "lucide-react";

import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
} from "@/components/ui/card";

interface UploadPanelProps {
  file: File | null;
  setFile: (file: File | null) => void;
}

const ACCEPTED_TYPES =
  ".pdf,.doc,.docx,.png,.jpg,.jpeg,.txt";

export default function UploadPanel({
  file,
  setFile,
}: UploadPanelProps) {
  const inputRef =
    useRef<HTMLInputElement>(null);

  function handleFile(file: File | null) {
    if (!file) return;

    setFile(file);
  }

  function onInputChange(
    event: React.ChangeEvent<HTMLInputElement>
  ) {
    handleFile(event.target.files?.[0] ?? null);
  }

  function onDrop(
    event: React.DragEvent<HTMLDivElement>
  ) {
    event.preventDefault();

    handleFile(
      event.dataTransfer.files?.[0] ?? null
    );
  }

  function onDragOver(
    event: React.DragEvent<HTMLDivElement>
  ) {
    event.preventDefault();
  }

  function removeFile() {
    setFile(null);

    if (inputRef.current) {
      inputRef.current.value = "";
    }
  }

  return (
    <div className="space-y-8">

      <Card
        className="cursor-pointer border-2 border-dashed border-border hover:border-primary"
        onClick={() =>
          inputRef.current?.click()
        }
        onDrop={onDrop}
        onDragOver={onDragOver}
      >
        <CardContent className="flex flex-col items-center justify-center py-20 text-center">

          <input
            hidden
            ref={inputRef}
            type="file"
            accept={ACCEPTED_TYPES}
            onChange={onInputChange}
          />

          <div className="mb-6 rounded-2xl bg-primary/10 p-5">
            <UploadCloud className="h-10 w-10 text-primary" />
          </div>

          <h3 className="text-2xl font-semibold">
            Upload your document
          </h3>

          <p className="mt-3 max-w-md text-muted-foreground">
            Drag & drop your document here or browse
            your computer to begin AI analysis.
          </p>

          <Button
            type="button"
            variant="premium"
            className="mt-8"
          >
            Browse Files
          </Button>

          <p className="mt-6 text-sm text-muted-foreground">
            Supports PDF, DOCX, PNG, JPG and TXT
          </p>

        </CardContent>
      </Card>

      {file && (

        <Card>

          <CardContent className="flex items-center justify-between p-6">

            <div className="flex items-center gap-4">

              <div className="rounded-xl bg-primary/10 p-3">
                <FileText className="h-6 w-6 text-primary" />
              </div>

              <div>

                <p className="font-medium">
                  {file.name}
                </p>

                <p className="text-sm text-muted-foreground">
                  {(file.size / 1024).toFixed(2)} KB
                </p>

              </div>

            </div>

            <Button
              variant="ghost"
              size="icon"
              onClick={removeFile}
            >
              <Trash2 className="h-5 w-5" />
            </Button>

          </CardContent>

        </Card>

      )}

    </div>
  );
}