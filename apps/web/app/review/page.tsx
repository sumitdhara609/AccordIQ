import Link from "next/link";

export default function ReviewPage() {
  return (
    <main className="mx-auto flex min-h-screen max-w-5xl items-center justify-center px-6 py-10">
      <section className="w-full max-w-xl rounded-2xl border border-gray-200 bg-white p-8 text-center shadow-sm">
        <h1 className="text-2xl font-semibold text-gray-900">
          Document Review
        </h1>

        <p className="mt-3 text-sm leading-6 text-gray-500">
          Select a document from your document library to begin the review
          workflow.
        </p>

        <Link
          href="/documents"
          className="
            mt-6
            inline-flex
            items-center
            justify-center
            rounded-xl
            bg-gray-900
            px-5
            py-3
            text-sm
            font-semibold
            text-white
            transition
            hover:bg-gray-800
          "
        >
          Go to Documents
        </Link>
      </section>
    </main>
  );
}