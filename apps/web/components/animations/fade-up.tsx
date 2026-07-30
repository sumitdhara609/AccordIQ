"use client";

import { motion } from "framer-motion";
import { ReactNode } from "react";

import { durations, easing, fadeUp } from "@/lib/animations";

type FadeUpProps = {
  children: ReactNode;
  delay?: number;
  className?: string;
};

export function FadeUp({
  children,
  delay = 0,
  className,
}: FadeUpProps) {
  return (
    <motion.div
      className={className}
      variants={fadeUp}
      initial="initial"
      animate="animate"
      transition={{
        duration: durations.normal,
        ease: easing.smooth,
        delay,
      }}
    >
      {children}
    </motion.div>
  );
}