export type DocumentStatus =
  | "UPLOADED"
  | "PROCESSING"
  | "OCR_COMPLETED"
  | "REVIEW_REQUIRED"
  | "COMPLETED"
  | "FAILED";

export interface UploadDocumentResponse {
  id: string;
  originalFileName: string;
  storedFileName: string;
  contentType: string;
  fileSize: number;
  status: DocumentStatus;
}

export interface DocumentResponse {
  id: string;
  originalFileName: string;
  contentType: string;
  fileSize: number;
  status: DocumentStatus;
}