export interface DashboardStats {

  totalDocuments: number;

  uploadedToday: number;

  processing: number;

  completed: number;

  reviewRequired: number;

  failed: number;

}

export type DocumentStatus =
  | "UPLOADED"
  | "PROCESSING"
  | "OCR_COMPLETED"
  | "REVIEW_REQUIRED"
  | "COMPLETED"
  | "FAILED";

export interface RecentDocument {

  id: string;

  fileName: string;

  status: DocumentStatus;

  fileSize: number;

  uploadedAt: string;

}