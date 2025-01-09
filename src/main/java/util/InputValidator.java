package util;

public class InputValidator {
    public static boolean isValidAmount(String input) {
        try {
            double amount = Double.parseDouble(input);
            return amount >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}