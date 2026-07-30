"use client";

import { motion } from "framer-motion";
import {
  BadgeCheck,
  Calendar,
  Building2,
  DollarSign,
} from "lucide-react";

const rows = [
  {
    icon: Building2,
    label: "Vendor",
    value: "Amazon India",
  },
  {
    icon: Calendar,
    label: "Date",
    value: "28 Jul 2026",
  },
  {
    icon: DollarSign,
    label: "Total",
    value: "$128.44",
  },
  {
    icon: BadgeCheck,
    label: "Confidence",
    value: "99.2%",
  },
];

export function ExtractionPanel() {
  return (
    <motion.div
      initial={{
        opacity: 0,
        x: 40,
      }}
      animate={{
        opacity: 1,
        x: 0,
      }}
      transition={{
        duration: 0.8,
        delay: 0.5,
      }}
      className="
      absolute
      -right-24
      top-16
      w-72
      rounded-3xl
      border
      border-white/10
      bg-neutral-950/90
      p-6
      text-white
      shadow-2xl
      backdrop-blur-xl
      "
    >
      <p className="mb-6 text-sm font-semibold uppercase tracking-[0.25em] text-cyan-400">
        AI Extraction
      </p>

      <div className="space-y-5">
        {rows.map((row, index) => {
          const Icon = row.icon;

          return (
            <motion.div
              key={row.label}
              initial={{
                opacity: 0,
                y: 12,
              }}
              animate={{
                opacity: 1,
                y: 0,
              }}
              transition={{
                delay: 0.9 + index * 0.15,
              }}
              className="flex items-center justify-between"
            >
              <div className="flex items-center gap-3">
                <Icon className="h-4 w-4 text-cyan-400" />

                <span className="text-sm text-white/70">
                  {row.label}
                </span>
              </div>

              <span className="font-medium">
                {row.value}
              </span>
            </motion.div>
          );
        })}
      </div>
    </motion.div>
  );
}