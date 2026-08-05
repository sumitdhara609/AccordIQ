"use client";

import { useRef } from "react";

interface UploadPanelProps {
  file: File | null;
  setFile: (file: File | null) => void;
}

const ACCEPTED_TYPES = ".pdf,.doc,.docx,.png,.jpg,.jpeg,.txt";

export default function UploadPanel({
  file,
  setFile,
}: UploadPanelProps) {
  const inputRef = useRef<HTMLInputElement>(null);

  function handleFile(file: File | null) {
    if (!file) return;
    setFile(file);
  }

  function onInputChange(
    event: React.ChangeEvent<HTMLInputElement>
  ) {
    handleFile(event.target.files?.[0] ?? null);
  }

  function onDrop(event: React.DragEvent<HTMLDivElement>) {
    event.preventDefault();

    handleFile(event.dataTransfer.files?.[0] ?? null);
  }

  function onDragOver(event: React.DragEvent<HTMLDivElement>) {
    event.preventDefault();
  }

  function removeFile() {
    setFile(null);

    if (inputRef.current) {
      inputRef.current.value = "";
    }
  }

  return (
    <div className="space-y-6">
      <div
        onDrop={onDrop}
        onDragOver={onDragOver}
        onClick={() => inputRef.current?.click()}
        className="cursor-pointer rounded-2xl border-2 border-dashed p-12 text-center transition hover:border-black hover:bg-gray-50"
      >
        <input
          ref={inputRef}
          hidden
          type="file"
          accept={ACCEPTED_TYPES}
          onChange={onInputChange}
        />

        <div className="space-y-3">
          <div className="text-5xl">📄</div>

          <h3 className="text-xl font-semibold">
            Upload a document
          </h3>

          <p className="text-sm text-gray-500">
            Drag & drop your file here or click to browse
          </p>

          <p className="text-xs text-gray-400">
            PDF • DOCX • JPG • PNG • TXT
          </p>
        </div>
      </div>

      {file && (
        <div className="flex items-center justify-between rounded-xl border p-4">
          <div>
            <p className="font-medium">{file.name}</p>

            <p className="text-sm text-gray-500">
              {(file.size / 1024).toFixed(2)} KB
            </p>
          </div>

          <button
            type="button"
            onClick={removeFile}
            className="rounded-lg border px-4 py-2 hover:bg-gray-100"
          >
            Remove
          </button>
        </div>
      )}
    </div>
  );
}