"use client";

import { Background } from "./background";
import { HeroLeft } from "./hero-left";
import { HeroRight } from "./hero-right";

export function Hero() {
  return (
    <section className="relative isolate overflow-hidden">
      <Background />

      <div
        className="
        mx-auto
        flex
        min-h-screen
        max-w-7xl
        flex-col
        items-center
        justify-center
        gap-20
        px-6
        py-24
        lg:flex-row
        "
      >
        <HeroLeft />

        <HeroRight />
      </div>
    </section>
  );
}