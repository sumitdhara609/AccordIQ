export type ReviewStatus =
  | "PENDING"
  | "APPROVED"
  | "REJECTED";

export interface ReviewField {
  id: string;
  name: string;
  value: string;
  confidence: number | null;
}

export interface ReviewDocument {
  documentId: string;
  reviewId: string;
  documentName: string;
  status: ReviewStatus;
  reviewerComments: string | null;
  fields: ReviewField[];
}

export interface ReviewResponse {
  id: string;
  documentId: string;
  status: ReviewStatus;
  reviewerComments: string | null;
}

export interface DocumentAnalysisDetail {
  analysisId: string;
  documentId: string;
  documentType: string;
  summary: string | null;
  confidence: number | null;
  fields: ReviewField[];
}