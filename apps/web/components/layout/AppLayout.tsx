import type { ReactNode } from "react";

import Navbar from "./Navbar";

interface AppLayoutProps {
  children: ReactNode;
}

export default function AppLayout({
  children,
}: AppLayoutProps) {
  return (
    <>
      <Navbar />

      <main>{children}</main>
    </>
  );
}