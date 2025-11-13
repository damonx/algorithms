package com.damonx.designpatterns.strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Simple strategy pattern example without ugly if...else.
 */
public class DynamicFunctionalStrategy {

    // Store strategies in a mutable HashMap (instead of Map.of)
    private static final Map<String, Function<Double, Double>> discountStrategies = new HashMap<>();

    static {
        // Register default strategies
        register("NORMAL", price -> price);
        register("VIP", price -> price * 0.9);
        register("SUPER_VIP", price -> price * 0.8);
    }

    // Register or update a strategy
    public static void register(final String name, Function<Double, Double> strategy) {
        discountStrategies.put(name, strategy);
    }

    // Remove a strategy
    public static void remove(final String name) {
        discountStrategies.remove(name);
    }

    // Calculate discounted price
    public static double applyDiscount(final String type, double price) {
        return discountStrategies
            .getOrDefault(type, p -> p) // Default: no discount
            .apply(price);
    }

    public static void main(String[] args) {
        System.out.println("VIP member: " + applyDiscount("VIP", 100)); // 90.0
        System.out.println("Normal member: " + applyDiscount("NORMAL", 100)); // 100.0

        register("BLACK_FRIDAY", p -> p * 0.7);
        System.out.println("Black Friday price: " + applyDiscount("BLACK_FRIDAY", 100)); // 70.0

        register("VIP", p -> p * 0.85); // Update VIP to 15% off
        System.out.println("Updated VIP discount: " + applyDiscount("VIP", 100)); // 85.0

        remove("SUPER_VIP");
        System.out.println("After SUPER_VIP removal: " + applyDiscount("SUPER_VIP", 100)); // 100.0 (default)
    }
}
