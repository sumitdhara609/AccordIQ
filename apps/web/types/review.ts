export type ReviewStatus =
  | "PENDING"
  | "APPROVED"
  | "REJECTED";

export interface ReviewField {
  id: string;
  name: string;
  value: string;
  confidence: number;
}

export interface ReviewDocument {
  documentId: string;
  reviewId: string;

  documentName: string;

  status: ReviewStatus;

  fields: ReviewField[];
}