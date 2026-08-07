import { ReactNode } from "react";

import {
  Card,
  CardContent,
} from "@/components/ui/card";

interface MetricCardProps {
  icon: ReactNode;
  label: string;
  value: string | number;
  subtitle?: string;
}

export function MetricCard({
  icon,
  label,
  value,
  subtitle,
}: MetricCardProps) {
  return (
    <Card className="transition-all duration-300 hover:-translate-y-1 hover:shadow-xl">

      <CardContent className="p-6">

        <div className="mb-6 flex h-12 w-12 items-center justify-center rounded-2xl bg-primary/10 text-primary">
          {icon}
        </div>

        <p className="text-sm text-muted-foreground">
          {label}
        </p>

        <h2 className="mt-2 text-4xl font-bold tracking-tight">
          {value}
        </h2>

        {subtitle && (
          <p className="mt-3 text-sm text-muted-foreground">
            {subtitle}
          </p>
        )}

      </CardContent>

    </Card>
  );
}