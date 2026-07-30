"use client";

import { motion } from "framer-motion";
import { ScanLine } from "./scan-line";
import { ExtractionPanel } from "./extraction-panel";

const rows = [
  "Invoice #10491",
  "Amazon India",
  "Date: 28 Jul 2026",
  "Subtotal",
  "GST",
  "Total",
  "Items",
  "Shipping",
];

export function FloatingDocument() {
  return (
    <motion.div
      animate={{
        y: [0, -12, 0],
        rotate: [-1, 1, -1],
      }}
      transition={{
        repeat: Infinity,
        duration: 6,
        ease: "easeInOut",
      }}
      className="relative"
    >
      <div
        className="
        relative
        h-[620px]
        w-[430px]
        overflow-hidden
        rounded-3xl
        border
        border-white/10
        bg-white
        p-8
        shadow-2xl
        "
      >
        <div className="mb-8 flex items-center justify-between">
          <div>
            <h2 className="text-xl font-bold text-black">
              INVOICE
            </h2>

            <p className="text-sm text-neutral-500">
              Amazon India
            </p>
          </div>

          <div className="rounded-xl bg-cyan-100 px-3 py-2 text-sm font-semibold text-cyan-700">
            PDF
          </div>
        </div>

        <div className="space-y-5">
          {rows.map((row) => (
            <div key={row}>
              <div className="mb-2 h-2 w-full rounded-full bg-neutral-200" />

              <div className="h-2 w-2/3 rounded-full bg-neutral-100" />
            </div>
          ))}
        </div>

        <ScanLine />
      </div>

      <ExtractionPanel />
    </motion.div>
  );
}