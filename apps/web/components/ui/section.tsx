import * as React from "react";

import { cn } from "@/lib/utils";

interface SectionProps
  extends React.HTMLAttributes<HTMLElement> {}

export function Section({
  className,
  ...props
}: SectionProps) {
  return (
    <section
      className={cn(
        "py-20",
        className
      )}
      {...props}
    />
  );
}