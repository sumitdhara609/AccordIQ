import type {
  AnalyzeRequest,
  AnalyzeResponse,
} from "@/types/analyze";

class AnalyzeService {
  async analyze(
    request: AnalyzeRequest
  ): Promise<AnalyzeResponse> {
    // Simulate network latency
    await new Promise((resolve) => setTimeout(resolve, 1000));

    switch (request.type) {
      case "upload":
        return {
          success: true,
          summary: "Mock analysis completed for uploaded document.",
          confidence: 96,
          risks: [
            "No major issues detected.",
          ],
          recommendations: [
            "Review extracted information before exporting.",
          ],
        };

      case "text":
        return {
          success: true,
          summary: "Mock analysis completed for pasted text.",
          confidence: 94,
          risks: [],
          recommendations: [
            "Consider verifying important facts.",
          ],
        };

      case "url":
        return {
          success: true,
          summary: "Mock analysis completed for website.",
          confidence: 92,
          risks: [],
          recommendations: [
            "Check that the webpage is publicly accessible.",
          ],
        };

      default:
        throw new Error("Unsupported analyze mode.");
    }
  }
}

const analyzeService = new AnalyzeService();

export default analyzeService;