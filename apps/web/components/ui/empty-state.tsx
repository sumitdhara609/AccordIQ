import { ReactNode } from "react";

interface EmptyStateProps {
  icon: ReactNode;
  title: string;
  description: string;
}

export function EmptyState({
  icon,
  title,
  description,
}: EmptyStateProps) {
  return (
    <div className="flex flex-col items-center justify-center rounded-3xl border border-dashed p-16 text-center">

      <div className="mb-6 rounded-2xl bg-primary/10 p-5 text-primary">
        {icon}
      </div>

      <h3 className="text-2xl font-semibold">
        {title}
      </h3>

      <p className="mt-3 max-w-md leading-7 text-muted-foreground">
        {description}
      </p>

    </div>
  );
}