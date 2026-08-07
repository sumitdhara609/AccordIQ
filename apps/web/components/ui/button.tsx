import { Button as ButtonPrimitive } from "@base-ui/react/button";
import { cva, type VariantProps } from "class-variance-authority";

import { cn } from "@/lib/utils";

const buttonVariants = cva(
  "group/button inline-flex shrink-0 items-center justify-center rounded-xl border border-transparent bg-clip-padding text-sm font-medium whitespace-nowrap transition-all duration-300 ease-out outline-none select-none focus-visible:border-ring focus-visible:ring-4 focus-visible:ring-ring/40 active:scale-[0.98] disabled:pointer-events-none disabled:opacity-50 disabled:hover:scale-100 aria-invalid:border-destructive aria-invalid:ring-4 aria-invalid:ring-destructive/20 dark:aria-invalid:border-destructive/50 dark:aria-invalid:ring-destructive/40 [&_svg]:pointer-events-none [&_svg]:shrink-0 [&_svg:not([class*='size-'])]:size-4",
  {
    variants: {
      variant: {
        default:
          "bg-neutral-950 text-white shadow-sm hover:bg-black hover:-translate-y-0.5 hover:shadow-xl",

        premium:
          "bg-gradient-to-r from-slate-950 via-black to-slate-800 text-white shadow-lg hover:-translate-y-0.5 hover:shadow-2xl",

        glass:
          "border border-border/60 bg-background/70 backdrop-blur-xl shadow-sm hover:bg-background/90 hover:shadow-lg",

        soft:
          "bg-muted text-foreground hover:bg-accent",

        outline:
          "border-border bg-background hover:bg-muted hover:text-foreground dark:border-input dark:bg-input/30 dark:hover:bg-input/50",

        secondary:
          "bg-secondary text-secondary-foreground hover:bg-[color-mix(in_oklch,var(--secondary),var(--foreground)_5%)]",

        ghost:
          "hover:bg-muted hover:text-foreground dark:hover:bg-muted/50",

        destructive:
          "bg-destructive text-white shadow-sm hover:bg-destructive/90 hover:shadow-lg",

        link:
          "text-primary underline-offset-4 hover:underline",
      },

      size: {
        xs:
          "h-7 rounded-lg px-3 text-xs",

        sm:
          "h-9 rounded-lg px-4 text-sm",

        default:
          "h-10 px-5",

        lg:
          "h-11 px-6 text-base",

        xl:
          "h-12 px-8 text-base rounded-xl",

        hero:
          "h-14 px-10 rounded-2xl text-lg font-semibold",

        icon:
          "size-10",

        "icon-xs":
          "size-7 rounded-lg",

        "icon-sm":
          "size-9 rounded-xl",

        "icon-lg":
          "size-11 rounded-xl",
      },
    },

    defaultVariants: {
      variant: "default",
      size: "default",
    },
  }
);

function Button({
  className,
  variant,
  size,
  ...props
}: ButtonPrimitive.Props &
  VariantProps<typeof buttonVariants>) {
  return (
    <ButtonPrimitive
      data-slot="button"
      className={cn(
        buttonVariants({
          variant,
          size,
          className,
        })
      )}
      {...props}
    />
  );
}

export { Button, buttonVariants };