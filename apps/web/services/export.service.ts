import type {
  ExportDocument,
} from "@/types/export";

function downloadBlob(
  blob: Blob,
  fileName: string
) {
  const url = URL.createObjectURL(blob);

  const anchor =
    document.createElement("a");

  anchor.href = url;
  anchor.download = fileName;

  document.body.appendChild(anchor);
  anchor.click();

  anchor.remove();

  URL.revokeObjectURL(url);
}

function buildExportPayload(
  documentData: ExportDocument
) {
  return {
    id: documentData.id,
    fileName: documentData.fileName,
    documentType:
      documentData.documentType ?? null,
    summary:
      documentData.summary ?? null,
    status:
      documentData.status ?? null,
    fields: documentData.fields.map(
      (field) => ({
        name: field.name,
        value: field.value,
        confidence:
          field.confidence ?? null,
      })
    ),
  };
}

export const exportService = {
  downloadJson(
    documentData: ExportDocument
  ) {
    const payload =
      buildExportPayload(documentData);

    const json = JSON.stringify(
      payload,
      null,
      2
    );

    const blob = new Blob(
      [json],
      {
        type: "application/json",
      }
    );

    downloadBlob(
      blob,
      `${documentData.fileName}.json`
    );
  },

  downloadCsv(
    documentData: ExportDocument
  ) {
    const rows = [
      ["Field", "Value", "Confidence"],
      ...documentData.fields.map(
        (field) => [
          field.name,
          field.value,
          field.confidence == null
            ? ""
            : `${field.confidence}%`,
        ]
      ),
    ];

    const csv = rows
      .map((row) =>
        row
          .map((value) => {
            const escaped =
              String(value).replace(
                /"/g,
                '""'
              );

            return `"${escaped}"`;
          })
          .join(",")
      )
      .join("\n");

    const blob = new Blob(
      [csv],
      {
        type: "text/csv;charset=utf-8;",
      }
    );

    downloadBlob(
      blob,
      `${documentData.fileName}.csv`
    );
  },
};