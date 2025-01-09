package model;

import java.util.Date;

public class Operation {
    private double amount;
    private String category;
    private Date date;
    private boolean isIncome;

    public Operation(double amount, String category, boolean isIncome) {
        this.amount = amount;
        this.category = category;
        this.date = new Date();
        this.isIncome = isIncome;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public Date getDate() {
        return date;
    }

    public boolean isIncome() {
        return isIncome;
    }
}

