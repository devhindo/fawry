package com.ecommerce.service;

import com.ecommerce.model.Shippable;
import java.util.List;

/**
 * Service for handling shipping logistics
 * Follows Single Responsibility Principle
 */
public class ShippingService {
    private static final double SHIPPING_RATE_PER_KG = 10.0;
    private static final double BASE_SHIPPING_FEE = 5.0;

    public void processShipment(List<Shippable> shippableItems) {
        if (shippableItems.isEmpty()) {
            return;
        }

        System.out.println("** Shipment notice **");
        
        double totalWeight = 0.0;
        for (Shippable item : shippableItems) {
            double itemWeight = item.getWeight();
            totalWeight += itemWeight;
            
            // Format weight in grams if less than 1kg
            String weightStr = itemWeight < 1.0 ? 
                String.format("%.0fg", itemWeight * 1000) : 
                String.format("%.1fkg", itemWeight);
            
            System.out.println(String.format("1x %s %s", item.getName(), weightStr));
        }
        
        System.out.println(String.format("Total package weight %.1fkg", totalWeight));
    }

    public double calculateShippingCost(List<Shippable> shippableItems) {
        if (shippableItems.isEmpty()) {
            return 0.0;
        }

        double totalWeight = shippableItems.stream()
                                         .mapToDouble(Shippable::getWeight)
                                         .sum();
        
        return BASE_SHIPPING_FEE + (totalWeight * SHIPPING_RATE_PER_KG);
    }
}
