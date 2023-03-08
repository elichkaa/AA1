package models.core.tiles;

import models.core.Coordinates;
import models.core.Vegetable;

import java.util.*;
import java.util.stream.Collectors;

public class Barn extends Tile {
    private final static int storageCapacity = -1;
    private final static int xCoordinate = 0;
    private final static int yCoordinate = 0;
    private final static int INITIAL_COUNTDOWN = 6;
    private HashMap<Vegetable, Integer> storedVegetables;
    private int countdown;

    public Barn() {
        super(new Coordinates(xCoordinate, yCoordinate));
        this.capacity = storageCapacity;
        this.allowedVegetables = new ArrayList<>();
        this.storedVegetables = new HashMap<>() {{
            put(Vegetable.TOMATO, 1);
            put(Vegetable.SALAD, 1);
            put(Vegetable.CARROT, 1);
            put(Vegetable.MUSHROOM, 1);
        }};
        this.countdown = 6;
        this.abbreviation = "B";
    }

    public void updateCountdownIfAvailable() {
        if (countdown == 0) {
            // vegetables are spoilt and they are removed
            this.storedVegetables = new HashMap<>();
        }
        if (this.countdown > 0) {
            this.countdown--;
        }
    }

    public void addVegetable(Vegetable vegetable) {
        if (this.storedVegetables.size() == 0) {
            this.countdown = INITIAL_COUNTDOWN;
        }
        this.storedVegetables.put(vegetable, 1);
    }

    public boolean areVegetablesSpoiled() {
        return countdown == 0;
    }

    public List<Vegetable> getRemainingVegetablesAfterSell(List<String> input) {
        List<Vegetable> soldVegetables = new ArrayList<>();
        // TODO: implement support for all command
        for (String vegetableName : input) {
            soldVegetables.add(Enum.valueOf(Vegetable.class, vegetableName));
        }
        // TODO: possible error if the soldVegetable is not contained in the storedVegetables
        /*for (Vegetable soldVegetable : soldVegetables) {
            Vegetable firstMatchedVegetable = this.storedVegetables.stream()
                    .filter(x -> x.getName().equals(soldVegetable.getName()))
                    .findFirst()
                    .orElse(null);
            this.storedVegetables.remove(firstMatchedVegetable);
        }*/
        return this.storedVegetables.keySet().stream().toList();
    }

    public List<String> getStringRepresentationAsList() {
        char[] emptyRow = new char[5];
        Arrays.fill(emptyRow, ' ');
        return Arrays.asList(this.getRow(String.valueOf(emptyRow)),
                this.getRow(String.format(" %s %d ", this.abbreviation, this.countdown)),
                this.getRow(String.valueOf(emptyRow)));
    }

    public List<String> getSoldVegetablesAfterRound() {
        return null;
    }

    public String getBarnInformation(int gold) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Barn");
        HashMap<Vegetable, Integer> sortedVegetables = this.getSortedVegetables();
        String padding = " ".repeat(6 + String.valueOf(gold).length());
        if (!storedVegetables.isEmpty()) {
            padding = this.calculatePadding(
                    new ArrayList<>() {{
                        add(gold);
                        addAll(sortedVegetables.values());
                    }},
                    new ArrayList<>() {{
                        addAll(sortedVegetables.keySet().stream().map(Vegetable::getPlural).toList());
                    }}
            );
            stringBuilder.append(" (spoils in ").append(this.countdown).append(" turns)").append(System.lineSeparator());
            for (Vegetable vegetable : sortedVegetables.keySet()) {
                if (sortedVegetables.get(vegetable) != 0) {
                    stringBuilder.append(this.getPaddedString(vegetable.getPlural() + ":",
                                    String.valueOf(sortedVegetables.get(vegetable)), padding))
                            .append(System.lineSeparator());
                }
            }
            stringBuilder.append("-".repeat(padding.length())).append(System.lineSeparator());
            stringBuilder.append(this.getPaddedString("Sum:",
                            String.valueOf(this.storedVegetables.values().stream().mapToInt(Integer::intValue).sum()),
                            padding))
                    .append(System.lineSeparator());
        }
        stringBuilder.append(System.lineSeparator()).append(this.getPaddedString("Gold:",
                String.valueOf(gold),
                padding));
        return stringBuilder.toString();
    }

    private HashMap<Vegetable, Integer> getSortedVegetables() {
        return this.storedVegetables.entrySet().stream()
                .sorted(Map.Entry.<Vegetable, Integer>comparingByValue()
                        .thenComparing(Map.Entry.comparingByKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> newValue, LinkedHashMap::new));
    }

    private String calculatePadding(List<Integer> numbers, List<String> names) {
        numbers.sort(Comparator.reverseOrder());
        names.sort(Comparator.comparing(String::length, Comparator.reverseOrder()));
        return " ".repeat(names.get(0).length() + 2 + String.valueOf(numbers.get(0)).length());
    }

    private String getPaddedString(String name, String number, String padding) {
        return name +
                padding.substring(name.length(), padding.length() - number.length())
                + number;
    }
}
