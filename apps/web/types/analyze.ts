export type AnalyzeMode =
    | "upload"
    | "text"
    | "url";

export interface AnalyzeRequest {
  type: AnalyzeMode;

  file?: File | null;

  text?: string;

  url?: string;
}