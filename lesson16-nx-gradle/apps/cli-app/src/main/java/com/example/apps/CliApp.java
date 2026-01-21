package com.example.apps;

import com.example.core.utils.StringUtils;

public class CliApp {
    public static void main(String[] args) {
        System.out.println("=== CLI App (Command-Line Interface) ===");
        System.out.println("This app demonstrates Nx-styled workspace organization");
        System.out.println("Dependencies: core-utils");

        System.out.println("\n--- Using core-utils ---");
        System.out.println("Reversed: " + StringUtils.reverse("cli-app"));
        System.out.println("Capitalized: " + StringUtils.capitalize("cli"));

        System.out.println("\n✓ CLI app running successfully");
    }
}
