import type { ReviewDocument } from "@/types/review";

const API =
  process.env.NEXT_PUBLIC_API_BASE_URL;

class ReviewService {

  async getReview(
    documentId: string
  ): Promise<ReviewDocument> {

    const response = await fetch(
      `${API}/review/${documentId}`,
      {
        cache: "no-store",
      }
    );

    if (!response.ok) {
      throw new Error(
        "Failed to load review."
      );
    }

    const json =
      await response.json();

    return json.data;
  }

  async approve(
    documentId: string,
    comments: string
  ) {

    return fetch(
      `${API}/review/${documentId}/approve`,
      {
        method: "POST",
        headers: {
          "Content-Type":
            "application/json",
        },
        body: JSON.stringify({
          comments,
        }),
      }
    );
  }

  async reject(
    documentId: string,
    comments: string
  ) {

    return fetch(
      `${API}/review/${documentId}/reject`,
      {
        method: "POST",
        headers: {
          "Content-Type":
            "application/json",
        },
        body: JSON.stringify({
          comments,
        }),
      }
    );
  }

}

export const reviewService =
  new ReviewService();