import { ReactNode } from "react";

import { cn } from "@/lib/utils";

interface PageHeaderProps {
  badge?: ReactNode;
  title: string;
  description: string;
  actions?: ReactNode;
  className?: string;
}

export function PageHeader({
  badge,
  title,
  description,
  actions,
  className,
}: PageHeaderProps) {
  return (
    <section
      className={cn(
        "flex flex-col gap-8 lg:flex-row lg:items-end lg:justify-between",
        className
      )}
    >
      <div className="max-w-3xl">

        {badge && (
          <div className="mb-5">
            {badge}
          </div>
        )}

        <h1 className="text-4xl font-bold tracking-tight lg:text-5xl">
          {title}
        </h1>

        <p className="mt-4 text-lg leading-8 text-muted-foreground">
          {description}
        </p>

      </div>

      {actions && (
        <div className="flex items-center gap-3">
          {actions}
        </div>
      )}
    </section>
  );
}