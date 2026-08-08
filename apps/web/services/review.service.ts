import type {
  DocumentAnalysisDetail,
  ReviewResponse,
} from "@/types/review";

import type {
  DocumentResponse,
} from "@/types/document";

const API_BASE_URL =
  process.env.NEXT_PUBLIC_API_BASE_URL;

async function parseResponse<T>(
  response: Response
): Promise<T> {
  const body = await response.json();

  if (!response.ok || !body.success) {
    throw new Error(
      body.message ?? "Request failed."
    );
  }

  return body.data as T;
}

export const reviewService = {
  async getAnalysis(
    documentId: string
  ): Promise<DocumentAnalysisDetail> {
    const response = await fetch(
      `${API_BASE_URL}/document-analyses/${documentId}`,
      {
        cache: "no-store",
      }
    );

    return parseResponse<DocumentAnalysisDetail>(
      response
    );
  },

  async getDocument(
    documentId: string
  ): Promise<DocumentResponse> {
    const response = await fetch(
      `${API_BASE_URL}/documents/${documentId}`,
      {
        cache: "no-store",
      }
    );

    return parseResponse<DocumentResponse>(
      response
    );
  },

  async getReview(
    documentId: string
  ): Promise<ReviewResponse> {
    const response = await fetch(
      `${API_BASE_URL}/reviews/${documentId}`,
      {
        cache: "no-store",
      }
    );

    return parseResponse<ReviewResponse>(
      response
    );
  },

  async updateField(
    fieldId: string,
    value: string
  ) {
    const response = await fetch(
      `${API_BASE_URL}/document-fields/${fieldId}`,
      {
        method: "PATCH",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          value,
        }),
      }
    );

    return parseResponse(response);
  },

  async approve(
    documentId: string,
    comments: string
  ): Promise<ReviewResponse> {
    const response = await fetch(
      `${API_BASE_URL}/reviews/${documentId}/approve`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          comments,
        }),
      }
    );

    return parseResponse<ReviewResponse>(
      response
    );
  },

  async reject(
    documentId: string,
    comments: string
  ): Promise<ReviewResponse> {
    const response = await fetch(
      `${API_BASE_URL}/reviews/${documentId}/reject`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          comments,
        }),
      }
    );

    return parseResponse<ReviewResponse>(
      response
    );
  },
};