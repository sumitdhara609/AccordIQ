import type { DocumentStatus } from "@/types/document";

const API_BASE_URL =
  process.env.NEXT_PUBLIC_API_BASE_URL ??
  "http://localhost:8080/api/v1";

export interface DocumentResponse {
  id: string;
  originalFileName: string;
  contentType: string;
  fileSize: number;
  status: DocumentStatus;
}

interface ApiResponse<T> {
  success: boolean;
  message: string;
  data: T;
}

export interface SearchDocumentsRequest {
  keyword?: string;
  status?: DocumentStatus;
}

class DocumentService {
  async getAllDocuments(): Promise<DocumentResponse[]> {
    const response = await fetch(`${API_BASE_URL}/documents`, {
      cache: "no-store",
    });

    if (!response.ok) {
      throw new Error("Failed to load documents.");
    }

    const json: ApiResponse<DocumentResponse[]> =
      await response.json();

    return json.data;
  }

  async getDocument(
    id: string
  ): Promise<DocumentResponse> {
    const response = await fetch(
      `${API_BASE_URL}/documents/${id}`,
      {
        cache: "no-store",
      }
    );

    if (!response.ok) {
      throw new Error("Failed to load document.");
    }

    const json: ApiResponse<DocumentResponse> =
      await response.json();

    return json.data;
  }

  async searchDocuments(
    request: SearchDocumentsRequest
  ): Promise<DocumentResponse[]> {
    const params = new URLSearchParams();

    if (request.keyword?.trim()) {
      params.append("keyword", request.keyword);
    }

    if (request.status) {
      params.append("status", request.status);
    }

    const response = await fetch(
      `${API_BASE_URL}/documents/search?${params.toString()}`,
      {
        cache: "no-store",
      }
    );

    if (!response.ok) {
      throw new Error("Search failed.");
    }

    const json: ApiResponse<DocumentResponse[]> =
      await response.json();

    return json.data;
  }

  async deleteDocument(
    id: string
  ): Promise<void> {
    const response = await fetch(
      `${API_BASE_URL}/documents/${id}`,
      {
        method: "DELETE",
      }
    );

    if (!response.ok) {
      throw new Error("Failed to delete document.");
    }
  }
}

export const documentService =
  new DocumentService();