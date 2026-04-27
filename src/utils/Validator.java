package utils;

public class Validator {
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    public static boolean isValidAmount(String amountStr) {
        try {
            double amt = Double.parseDouble(amountStr);
            return amt > 0;
        } catch (NumberFormatException e) { return false; }
    }
}
