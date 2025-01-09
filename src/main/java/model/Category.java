package model;

public class Category {
    private String name;
    private boolean isIncome;

    public Category(String name, boolean isIncome) {
        this.name = name;
        this.isIncome = isIncome;
    }

    public String getName() {
        return name;
    }

    public boolean isIncome() {
        return isIncome;
    }
}
