"use client";

import { FileText, Trash2 } from "lucide-react";
import { useMemo } from "react";

import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
} from "@/components/ui/card";

interface TextPanelProps {
  text: string;
  setText: (text: string) => void;
}

export default function TextPanel({
  text,
  setText,
}: TextPanelProps) {

  const wordCount = useMemo(() => {

    if (!text.trim()) {
      return 0;
    }

    return text
      .trim()
      .split(/\s+/)
      .length;

  }, [text]);

  const characterCount = text.length;

  return (
    <Card>

      <CardContent className="space-y-6 p-8">

        <div className="flex items-center gap-3">

          <div className="rounded-xl bg-primary/10 p-3">
            <FileText className="h-5 w-5 text-primary" />
          </div>

          <div>

            <h3 className="text-xl font-semibold">
              Paste Document Text
            </h3>

            <p className="text-sm text-muted-foreground">
              Paste contracts, agreements, invoices or any document text for AI analysis.
            </p>

          </div>

        </div>

        <div className="relative">

          <textarea
            value={text}
            onChange={(e) => setText(e.target.value)}
            placeholder="Paste your document here..."
            className="
              min-h-[320px]
              w-full
              resize-none
              rounded-2xl
              border
              border-border
              bg-background
              p-6
              text-base
              leading-8
              outline-none
              transition-all
              duration-300
              focus:border-primary
              focus:ring-4
              focus:ring-primary/10
            "
          />

        </div>

        <div className="flex flex-wrap items-center justify-between gap-4">

          <div className="flex gap-6 text-sm text-muted-foreground">

            <span>
              <strong>{wordCount}</strong> words
            </span>

            <span>
              <strong>{characterCount}</strong> characters
            </span>

          </div>

          <Button
            type="button"
            variant="soft"
            onClick={() => setText("")}
            disabled={!text}
          >
            <Trash2 className="mr-2 h-4 w-4" />
            Clear
          </Button>

        </div>

      </CardContent>

    </Card>
  );
}