"use client";

interface TextPanelProps {
  text: string;
  setText: (text: string) => void;
}

export default function TextPanel({
  text,
  setText,
}: TextPanelProps) {
  return (
    <div className="space-y-4">
      <textarea
        value={text}
        onChange={(e) => setText(e.target.value)}
        placeholder="Paste your document text here..."
        rows={12}
        className="w-full rounded-xl border p-4 outline-none focus:ring-2 focus:ring-black"
      />

      <div className="flex items-center justify-between text-sm text-gray-500">
        <span>{text.length} characters</span>

        <button
          type="button"
          onClick={() => setText("")}
          className="rounded-md border px-3 py-1 hover:bg-gray-100"
        >
          Clear
        </button>
      </div>
    </div>
  );
}