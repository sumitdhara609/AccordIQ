"use client";

import { ArrowUpRight, Upload } from "lucide-react";

export function UploadButton() {
  return (
    <button
      className="
      group
      inline-flex
      items-center
      gap-3
      rounded-2xl
      border
      border-white/10
      bg-white
      px-6
      py-4
      font-medium
      text-black
      transition-all
      duration-300
      hover:scale-105
      hover:shadow-2xl
      hover:shadow-cyan-500/20
      active:scale-95
      "
    >
      <Upload className="h-5 w-5" />

      Upload Document

      <ArrowUpRight
        className="
        h-5
        w-5
        transition-transform
        duration-300
        group-hover:translate-x-1
        group-hover:-translate-y-1
        "
      />
    </button>
  );
}