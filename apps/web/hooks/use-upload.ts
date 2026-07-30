"use client";

import { useState } from "react";
import axios from "axios";

import { documentApi } from "@/lib/api/documents";

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
  const [isUploading, setIsUploading] = useState(false);

  async function upload() {
    if (!selectedFile) {
      setError("Please select a file first.");
      return;
    }

    try {
      setError(null);
      setIsUploading(true);

      console.log("Uploading:", selectedFile.name);

      const response = await documentApi.upload(selectedFile);

      console.log("Upload successful:", response);

      setSelectedFile(null);

      alert("Document uploaded successfully.");
    } catch (err) {
      if (axios.isAxiosError(err)) {
        console.error("Upload failed");
        console.error("Status:", err.response?.status);
        console.error("Response:", err.response?.data);
        console.error("Message:", err.message);

        setError(
          err.response?.data?.message ??
            `Upload failed${err.response?.status ? ` (${err.response.status})` : ""}.`
        );
      } else {
        console.error(err);
        setError("An unexpected error occurred.");
      }
    } finally {
      setIsUploading(false);
    }
  }

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
    isUploading,
    setIsDragging,
    selectFile,
    upload,
  };
}