"use client";

export function Background() {
  return (
    <>
      <div className="absolute inset-0 -z-20 bg-[#04070d]" />

      <div
        className="
        absolute
        left-[-250px]
        top-[-250px]
        -z-10
        h-[500px]
        w-[500px]
        rounded-full
        bg-cyan-500/20
        blur-[140px]
        "
      />

      <div
        className="
        absolute
        bottom-[-250px]
        right-[-250px]
        -z-10
        h-[500px]
        w-[500px]
        rounded-full
        bg-blue-600/20
        blur-[160px]
        "
      />

      <div
        className="
        absolute
        inset-0
        -z-10
        opacity-[0.06]
        [background-image:linear-gradient(rgba(255,255,255,.1)_1px,transparent_1px),linear-gradient(90deg,rgba(255,255,255,.1)_1px,transparent_1px)]
        [background-size:48px_48px]
        "
      />
    </>
  );
}