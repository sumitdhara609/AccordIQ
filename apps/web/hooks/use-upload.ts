"use client";

import { useState } from "react";

const MAX_FILE_SIZE = 10 * 1024 * 1024;

const ALLOWED_TYPES = [
  "application/pdf",
  "image/png",
  "image/jpeg",
];

export function useUpload() {
  const [selectedFile, setSelectedFile] = useState<File | null>(null);
  const [error, setError] = useState<string | null>(null);
  const [isDragging, setIsDragging] = useState(false);

  function validate(file: File) {
    if (!ALLOWED_TYPES.includes(file.type)) {
      return "Only PDF, PNG and JPG files are supported.";
    }

    if (file.size > MAX_FILE_SIZE) {
      return "File size must not exceed 10 MB.";
    }

    return null;
  }

  function selectFile(file: File) {
    const validationError = validate(file);

    if (validationError) {
      setError(validationError);
      setSelectedFile(null);
      return;
    }

    setError(null);
    setSelectedFile(file);
  }

  return {
    selectedFile,
    error,
    isDragging,
    setIsDragging,
    selectFile,
  };
}