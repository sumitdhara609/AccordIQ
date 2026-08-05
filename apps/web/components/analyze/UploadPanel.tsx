"use client";

interface UploadPanelProps {
  file: File | null;
  setFile: (file: File | null) => void;
}

export default function UploadPanel({
  file,
  setFile,
}: UploadPanelProps) {
  function handleFileChange(
    event: React.ChangeEvent<HTMLInputElement>
  ) {
    const selected = event.target.files?.[0] ?? null;
    setFile(selected);
  }

  return (
    <div className="rounded-xl border border-dashed p-8 text-center">
      <input
        id="document-upload"
        type="file"
        accept=".pdf,.doc,.docx,.png,.jpg,.jpeg,.txt"
        className="hidden"
        onChange={handleFileChange}
      />

      <label
        htmlFor="document-upload"
        className="cursor-pointer rounded-lg border px-4 py-2 hover:bg-gray-100"
      >
        Choose Document
      </label>

      {file ? (
        <div className="mt-6 space-y-1">
          <p className="font-medium">{file.name}</p>
          <p className="text-sm text-gray-500">
            {(file.size / 1024).toFixed(2)} KB
          </p>
        </div>
      ) : (
        <p className="mt-6 text-sm text-gray-500">
          No document selected.
        </p>
      )}
    </div>
  );
}