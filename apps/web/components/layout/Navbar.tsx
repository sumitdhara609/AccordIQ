"use client";

import Link from "next/link";
import { useEffect, useState } from "react";
import {
  FileText,
  LayoutDashboard,
  Moon,
  Sun,
} from "lucide-react";
import { useTheme } from "next-themes";

import { Button } from "@/components/ui/button";
import { Container } from "@/components/ui/container";

export default function Navbar() {
  const { theme, setTheme } = useTheme();
  const [mounted, setMounted] = useState(false);

  useEffect(() => {
    setMounted(true);
  }, []);

  const isDark = mounted && theme === "dark";

  return (
    <header className="border-b border-border bg-background/80 backdrop-blur-xl">
      <Container>
        <div className="flex h-16 items-center justify-between">
          <Link
            href="/"
            className="flex items-center gap-3"
            aria-label="AccordIQ home"
          >
            <div>
              <h1 className="text-lg font-semibold tracking-tight">
                AccordIQ
              </h1>

              <p className="text-xs text-muted-foreground">
                Document Intelligence
              </p>
            </div>
          </Link>

          <nav
            className="hidden items-center gap-2 md:flex"
            aria-label="Primary navigation"
          >
            <Link href="/dashboard">
              <Button variant="ghost">
                <LayoutDashboard className="mr-2 h-4 w-4" />
                Dashboard
              </Button>
            </Link>

            <Link href="/analyze">
              <Button variant="ghost">
                <FileText className="mr-2 h-4 w-4" />
                Analyze
              </Button>
            </Link>
          </nav>

          <Button
            variant="glass"
            size="icon"
            onClick={() =>
              setTheme(isDark ? "light" : "dark")
            }
            disabled={!mounted}
            aria-label={
              isDark
                ? "Switch to light theme"
                : "Switch to dark theme"
            }
            title={
              isDark
                ? "Switch to light theme"
                : "Switch to dark theme"
            }
          >
            {isDark ? (
              <Sun className="h-5 w-5" />
            ) : (
              <Moon className="h-5 w-5" />
            )}
          </Button>
        </div>
      </Container>
    </header>
  );
}