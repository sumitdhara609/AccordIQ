export type AnalyzeMode = "upload" | "text" | "url";

export interface AnalyzeRequest {
  type: AnalyzeMode;
  file?: File | null;
  text?: string;
  url?: string;
}

export interface AnalyzeResponse {
  summary: string;
  keyPoints: string[];
  risks: string[];
  recommendations: string[];
}