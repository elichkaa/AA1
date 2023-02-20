package models.core;

import models.core.tiles.Barn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Market {
    private final static int storageCapacity = -1;
    private final Map<Vegetable, Integer> market;
    private int[][] mushroomCarrotPriceConstants = new int[5][2];
    private int mushroomCarrotPriceIndex;
    private int tomatoSaladPriceIndex;
    private int[][] tomatoSaladPriceConstants = new int[5][2];

    public Market() {
        this.initializePriceConstants();
        this.market = new HashMap<>();
        this.updateMarket();
    }

    public void sortMarket(Barn barn, List<Vegetable> soldVegetables) {
        if (soldVegetables == null) {
            return;
        }
        List<Vegetable> remainingVegetablesAfterSell = barn.getRemainingVegetablesAfterSell(soldVegetables);
        Map<Vegetable, Long> groupedVegetablesByCount = remainingVegetablesAfterSell.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (Vegetable vegetable : groupedVegetablesByCount.keySet()) {
            this.adjustMarket(vegetable, groupedVegetablesByCount.get(vegetable).intValue() / 2);
        }
    }

    private void updateMarket() {
        this.market.put(Vegetable.TOMATO, this.tomatoSaladPriceConstants[this.tomatoSaladPriceIndex][0]);
        this.market.put(Vegetable.SALAD, this.tomatoSaladPriceConstants[this.tomatoSaladPriceIndex][1]);
        this.market.put(Vegetable.MUSHROOM, this.mushroomCarrotPriceConstants[this.mushroomCarrotPriceIndex][0]);
        this.market.put(Vegetable.CARROT, this.mushroomCarrotPriceConstants[this.mushroomCarrotPriceIndex][1]);
    }

    private void adjustMarket(Vegetable vegetable, int count) {
        for (int i = 0; i < count; i++) {
            switch (vegetable) {
                case CARROT -> this.modifyIndex(() -> this.mushroomCarrotPriceIndex--);
                case TOMATO -> this.modifyIndex(() -> this.tomatoSaladPriceIndex++);
                case SALAD -> this.modifyIndex(() -> this.tomatoSaladPriceIndex--);
                case MUSHROOM -> this.modifyIndex(() -> this.mushroomCarrotPriceIndex++);
            }
        }
    }

    private void modifyIndex(Supplier<Integer> supplier) {
        if (supplier.get() == -1) {
            return;
        } else if (supplier.get() == 5) {
            return;
        }
        supplier.get();
    }

    private void initializePriceConstants() {
        this.mushroomCarrotPriceConstants = new int[][]{
                {12, 3},
                {15, 2},
                {16, 2},
                {17, 2},
                {20, 1}
        };
        this.mushroomCarrotPriceIndex = 2;
        this.tomatoSaladPriceConstants = new int[][]{
                {3, 6},
                {5, 5},
                {6, 4},
                {7, 3},
                {9, 2}
        };
        this.tomatoSaladPriceIndex = 2;
    }
}
