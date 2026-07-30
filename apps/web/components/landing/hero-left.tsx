"use client";

import Balancer from "react-wrap-balancer";
import { CheckCircle2 } from "lucide-react";
import { UploadButton } from "./upload-button";

const features = [
  "OCR Powered",
  "AI Extraction",
  "Validation Engine",
];

export function HeroLeft() {
  return (
    <div className="max-w-xl">
      <span
        className="
        rounded-full
        border
        border-cyan-400/20
        bg-cyan-500/10
        px-4
        py-2
        text-sm
        text-cyan-300
        "
      >
        AI Document Intelligence Platform
      </span>

      <h1
        className="
        mt-8
        text-5xl
        font-black
        leading-tight
        tracking-tight
        md:text-7xl
        "
      >
        <Balancer>
          Understand Documents.
          <br />
          Not Just Text.
        </Balancer>
      </h1>

      <p
        className="
        mt-8
        text-lg
        leading-8
        text-white/70
        "
      >
        <Balancer>
          Transform invoices, receipts, contracts and PDFs into structured,
          validated data using OCR and AI.
        </Balancer>
      </p>

      <div className="mt-10">
        <UploadButton />
      </div>

      <div className="mt-12 flex flex-wrap gap-4">
        {features.map((feature) => (
          <div
            key={feature}
            className="
            flex
            items-center
            gap-2
            rounded-full
            border
            border-white/10
            bg-white/5
            px-4
            py-2
            text-sm
            text-white/80
            backdrop-blur-md
            "
          >
            <CheckCircle2 className="h-4 w-4 text-cyan-400" />

            {feature}
          </div>
        ))}
      </div>
    </div>
  );
}