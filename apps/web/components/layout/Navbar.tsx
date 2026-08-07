"use client";

import Link from "next/link";
import { FileText, LayoutDashboard, Moon, Sun } from "lucide-react";
import { useTheme } from "next-themes";

import { Button } from "@/components/ui/button";
import { Container } from "@/components/ui/container";

export default function Navbar() {
  const { theme, setTheme } = useTheme();

  return (
    <header className="sticky top-0 z-50 border-b border-border/60 bg-background/80 backdrop-blur-xl">
      <Container>
        <div className="flex h-16 items-center justify-between">
          <Link
            href="/"
            className="flex items-center gap-3"
          >
            <div className="flex h-10 w-10 items-center justify-center rounded-xl bg-primary text-primary-foreground shadow-sm">
              <FileText className="h-5 w-5" />
            </div>

            <div>
              <h1 className="text-lg font-semibold tracking-tight">
                AccordIQ
              </h1>

              <p className="text-xs text-muted-foreground">
                Document Intelligence
              </p>
            </div>
          </Link>

          <nav className="hidden items-center gap-2 md:flex">
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
              setTheme(theme === "dark" ? "light" : "dark")
            }
          >
            {theme === "dark" ? (
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