"use client";

import {
  Calendar,
  FileText,
  HardDrive,
} from "lucide-react";

import type { RecentDocument } from "@/types/dashboard";

import { Card, CardContent, CardHeader } from "@/components/ui/card";
import { EmptyState } from "@/components/ui/empty-state";
import { StatusPill } from "@/components/ui/status-pill";

interface RecentDocumentsProps {
  documents: RecentDocument[];
}

function formatFileSize(bytes: number): string {
  if (bytes < 1024) {
    return `${bytes} B`;
  }

  if (bytes < 1024 * 1024) {
    return `${(bytes / 1024).toFixed(1)} KB`;
  }

  if (bytes < 1024 * 1024 * 1024) {
    return `${(bytes / (1024 * 1024)).toFixed(1)} MB`;
  }

  return `${(bytes / (1024 * 1024 * 1024)).toFixed(1)} GB`;
}

function formatDate(date: string): string {
  return new Date(date).toLocaleString();
}

export default function RecentDocuments({
  documents,
}: RecentDocumentsProps) {
  if (documents.length === 0) {
    return (
      <EmptyState
        icon={<FileText className="h-8 w-8" />}
        title="No documents yet"
        description="Upload your first document to begin using AccordIQ."
      />
    );
  }

  return (
    <Card>

      <CardHeader>

        <h2 className="text-2xl font-semibold">
          Recent Documents
        </h2>

        <p className="text-sm text-muted-foreground">
          Latest uploaded documents.
        </p>

      </CardHeader>

      <CardContent className="p-0">

        <div className="divide-y">

          {documents.map((document) => (

            <div
              key={document.id}
              className="flex items-center justify-between px-6 py-5 transition-colors hover:bg-muted/40"
            >

              <div className="flex items-start gap-4">

                <div className="rounded-xl bg-primary/10 p-3 text-primary">

                  <FileText className="h-5 w-5" />

                </div>

                <div>

                  <h3 className="font-medium">

                    {document.fileName}

                  </h3>

                  <div className="mt-2 flex flex-wrap items-center gap-4 text-sm text-muted-foreground">

                    <span className="flex items-center gap-1">

                      <HardDrive className="h-4 w-4" />

                      {formatFileSize(document.fileSize)}

                    </span>

                    <span className="flex items-center gap-1">

                      <Calendar className="h-4 w-4" />

                      {formatDate(document.uploadedAt)}

                    </span>

                  </div>

                </div>

              </div>

              <StatusPill
                status={document.status as any}
              />

            </div>

          ))}

        </div>

      </CardContent>

    </Card>
  );
}