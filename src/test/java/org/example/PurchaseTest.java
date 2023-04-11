package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class PurchaseTest {

    Purchase purchase = new Purchase();

    @Test
    void should_return_correct_value() {
        //given

        //when && then
        Assertions.assertThat(purchase.calculateMinimumCost(List.of(1, 0, 0, 0, 0))).isEqualTo(8);
        Assertions.assertThat(purchase.calculateMinimumCost(List.of(1, 1, 0, 0, 0))).isEqualTo(15.2);
        Assertions.assertThat(purchase.calculateMinimumCost(List.of(1, 1, 1, 0, 0))).isEqualTo(21.6);
        Assertions.assertThat(purchase.calculateMinimumCost(List.of(1, 1, 1, 1, 0))).isEqualTo(25.6);
        Assertions.assertThat(purchase.calculateMinimumCost(List.of(1, 1, 1, 1, 1))).isEqualTo(30);

        Assertions.assertThat(purchase.calculateMinimumCost(List.of(2, 2, 2, 2, 2))).isEqualTo(60);
        Assertions.assertThat(purchase.calculateMinimumCost(List.of(2, 2, 2, 1, 1))).isEqualTo(51.2);
        Assertions.assertThat(purchase.calculateMinimumCost(List.of(2, 1, 0, 0, 0))).isEqualTo(8 + (8 * 2 * 0.95));
        Assertions.assertThat(purchase.calculateMinimumCost(List.of(2, 2, 0, 0, 0))).isEqualTo(2 * (8 * 2 * 0.95));
        Assertions.assertThat(purchase.calculateMinimumCost(List.of(2, 1, 2, 1, 0))).isEqualTo((8 * 4 * 0.8) + (8 * 2 * 0.95));
        Assertions.assertThat(purchase.calculateMinimumCost(List.of(1, 2, 1, 1, 1))).isEqualTo(8 + (8 * 5 * 0.75));

    }

    @Test
    void should_apply_discount() {
        //when & then
        Assertions.assertThat(purchase.applyDiscount(1)).isEqualTo(8);
        Assertions.assertThat(purchase.applyDiscount(2)).isEqualTo(15.2);
        Assertions.assertThat(purchase.applyDiscount(3)).isEqualTo(21.6);
        Assertions.assertThat(purchase.applyDiscount(4)).isEqualTo(25.6);
        Assertions.assertThat(purchase.applyDiscount(5)).isEqualTo(30);
    }

    @Test
    void should_decrement_books() {
        //when & then
        Assertions.assertThat(purchase.decrementBooks(new ArrayList<>(List.of(1, 1, 0, 0, 0)), 2)).isEmpty();
        Assertions.assertThat(purchase.decrementBooks(new ArrayList<>(List.of(1, 1, 0, 0, 0)), 1))
                .containsExactly(1);
    }
}