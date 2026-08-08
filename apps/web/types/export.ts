export interface ExportField {
  name: string;
  value: string;
  confidence?: number | null;
}

export interface ExportDocument {
  id: string;
  fileName: string;
  documentType?: string | null;
  summary?: string | null;
  fields: ExportField[];
  status?: string | null;
}