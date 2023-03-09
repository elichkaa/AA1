package models.core;

import models.core.tiles.Barn;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Market {
    private final HashMap<Vegetable, Integer> market;
    private int[][] mushroomCarrotPriceConstants = new int[5][2];
    private int mushroomCarrotPriceIndex;
    private int tomatoSaladPriceIndex;
    private int[][] tomatoSaladPriceConstants = new int[5][2];

    public Market() {
        this.initializePriceConstants();
        this.market = new HashMap<>();
        this.updateMarket();
    }

    public void sortMarket(Barn barn, List<String> soldVegetables) {
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
        this.market.put(Vegetable.MUSHROOM, this.mushroomCarrotPriceConstants[this.mushroomCarrotPriceIndex][0]);
        this.market.put(Vegetable.CARROT, this.mushroomCarrotPriceConstants[this.mushroomCarrotPriceIndex][1]);
        this.market.put(Vegetable.TOMATO, this.tomatoSaladPriceConstants[this.tomatoSaladPriceIndex][0]);
        this.market.put(Vegetable.SALAD, this.tomatoSaladPriceConstants[this.tomatoSaladPriceIndex][1]);
    }

    private void adjustMarket(Vegetable vegetable, int count) {
        for (int i = 0; i < count; i++) {
            switch (vegetable) {
                case MUSHROOM -> this.modifyIndex(() -> this.mushroomCarrotPriceIndex++);
                case CARROT -> this.modifyIndex(() -> this.mushroomCarrotPriceIndex--);
                case TOMATO -> this.modifyIndex(() -> this.tomatoSaladPriceIndex++);
                case SALAD -> this.modifyIndex(() -> this.tomatoSaladPriceIndex--);
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

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String padding = this.calculatePadding(new ArrayList<>(this.market.values()));
        for (Vegetable vegetable : getSortedKeys()) {
            stringBuilder.append(this.getPaddedString(vegetable.getPlural() + ":",
                    String.valueOf(market.get(vegetable)), padding)).append(System.lineSeparator());
        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    private String calculatePadding(List<Integer> numbers) {
        numbers.sort(Comparator.reverseOrder());
        return " ".repeat(Vegetable.MUSHROOM.getPlural().length() + 2 + String.valueOf(numbers.get(0)).length());
    }

    private String getPaddedString(String name, String number, String padding) {
        return name + padding.substring(name.length(), padding.length() - number.length()) + number;
    }

    private List<Vegetable> getSortedKeys() {
        return new ArrayList<>() {{
            add(Vegetable.MUSHROOM);
            add(Vegetable.CARROT);
            add(Vegetable.TOMATO);
            add(Vegetable.SALAD);
        }};
    }

    public int getVegetablePrice(Vegetable key) {
        return this.market.get(key);
    }
}
