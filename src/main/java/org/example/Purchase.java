package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Purchase {

    final double BOOK_PRICE = 8;

     Map<Integer, Double> DISCOUNTS = Map.of(
            1, 1d,
            2, 0.95d,
            3, 0.9d,
            4, 0.8d,
            5, 0.75d
    );

    Double calculateMinimumCost(List<Integer> purchase) {
        double totalCost = Double.MAX_VALUE;
        List<Integer> discounts = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        for (int i = 0; i < 10000; i++) {
            Collections.shuffle(discounts);
            double costCalculus = applyDiscounts(purchase, discounts);
            if (totalCost > costCalculus && costCalculus != 0)
                totalCost = costCalculus;
        }
        return totalCost;
    }

     double applyDiscounts(List<Integer> purchase, List<Integer> discounts) {
        double costCalculus = 0;
        int currentDiscount = 0;
        List<Integer> totalBooksPerVolume = purchase
                .stream()
                .filter(x -> x > 0)
                .collect(Collectors.toList());
        while (!totalBooksPerVolume.isEmpty() && currentDiscount < 5) {
            int discount = discounts.get(currentDiscount);
            if (discount > totalBooksPerVolume.size()) {
                currentDiscount++;
                continue;
            }
            costCalculus += applyDiscount(discount);
            totalBooksPerVolume = decrementBooks(totalBooksPerVolume, discount);
        }
        return costCalculus;
    }

    List<Integer> decrementBooks(List<Integer> totalBooksPerNumber, int discount) {
        for (int i = 0; i < totalBooksPerNumber.size(); i++) {
            if (i < discount)
                totalBooksPerNumber.set(i, totalBooksPerNumber.get(i) - 1);
        }
        return totalBooksPerNumber.stream()
                .filter(x -> x > 0)
                .collect(Collectors.toList());
    }

    Double applyDiscount(int discount) {
        return discount * BOOK_PRICE * DISCOUNTS.get(discount);
    }

}
