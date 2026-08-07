import * as React from "react";

import { cva, type VariantProps } from "class-variance-authority";

import { cn } from "@/lib/utils";

const badgeVariants = cva(
  "inline-flex items-center rounded-full px-3 py-1 text-xs font-medium transition-colors",
  {
    variants: {
      variant: {
        default:
          "bg-primary text-primary-foreground",

        secondary:
          "bg-secondary text-secondary-foreground",

        outline:
          "border border-border",

        success:
          "bg-green-100 text-green-700 dark:bg-green-900/30 dark:text-green-400",

        warning:
          "bg-amber-100 text-amber-700 dark:bg-amber-900/30 dark:text-amber-400",

        destructive:
          "bg-red-100 text-red-700 dark:bg-red-900/30 dark:text-red-400",
      },
    },

    defaultVariants: {
      variant: "default",
    },
  }
);

export function Badge({
  className,
  variant,
  ...props
}: React.HTMLAttributes<HTMLDivElement> &
  VariantProps<typeof badgeVariants>) {
  return (
    <div
      className={cn(
        badgeVariants({
          variant,
        }),
        className
      )}
      {...props}
    />
  );
}