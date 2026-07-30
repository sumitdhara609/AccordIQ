"use client";

import { motion } from "framer-motion";

export function ScanLine() {
  return (
    <motion.div
      initial={{ y: -250, opacity: 0 }}
      animate={{
        y: 520,
        opacity: [0, 1, 1, 0],
      }}
      transition={{
        duration: 3,
        repeat: Infinity,
        ease: "linear",
      }}
      className="absolute inset-x-0 z-20 flex justify-center"
    >
      <div className="relative h-[3px] w-[90%] rounded-full bg-cyan-400 shadow-[0_0_30px_6px_rgba(34,211,238,0.55)]">
        <div className="absolute left-0 top-1/2 h-20 w-full -translate-y-1/2 bg-gradient-to-b from-cyan-400/30 via-cyan-400/10 to-transparent blur-md" />
      </div>
    </motion.div>
  );
}