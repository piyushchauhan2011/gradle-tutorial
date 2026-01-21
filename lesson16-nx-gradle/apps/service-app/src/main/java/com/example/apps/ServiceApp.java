package com.example.apps;

import com.example.core.utils.StringUtils;
import com.example.data.User;

public class ServiceApp {
    public static void main(String[] args) {
        System.out.println("=== Service App (REST API Server) ===");
        System.out.println("This app demonstrates Nx-styled workspace organization");
        System.out.println("Dependencies: core-utils, data-models");

        System.out.println("\n--- Using core-utils ---");
        System.out.println("Capitalized: " + StringUtils.capitalize("service"));

        System.out.println("\n--- Using data-models ---");
        User user = new User("1", "Alice", "alice@service.com");
        System.out.println("Created user: " + user);

        System.out.println("\n✓ Service app running successfully");
    }
}
