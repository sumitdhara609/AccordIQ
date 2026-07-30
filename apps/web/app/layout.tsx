import type { Metadata } from "next";
import { Geist, Geist_Mono } from "next/font/google";

import { ThemeProvider } from "@/providers/theme-provider";

import "./globals.css";

const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

export const metadata: Metadata = {
  title: {
    default: "AccordIQ",
    template: "%s | AccordIQ",
  },
  description:
    "AI-powered document intelligence platform for OCR, extraction, validation, and review.",
  applicationName: "AccordIQ",
  keywords: [
    "AccordIQ",
    "OCR",
    "AI",
    "Document Intelligence",
    "Document Processing",
    "Invoice Extraction",
    "Receipt OCR",
    "Spring Boot",
    "Next.js",
  ],
  authors: [
    {
      name: "Sumit Dhara",
    },
  ],
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en" suppressHydrationWarning>
      <body
        className={`${geistSans.variable} ${geistMono.variable} antialiased`}
      >
        <ThemeProvider>
          {children}
        </ThemeProvider>
      </body>
    </html>
  );
}