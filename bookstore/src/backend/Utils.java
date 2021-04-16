package backend;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {
    // Validation for DateField
    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    // Check if the given string is num or double
    public static boolean isNumberOrDouble(String string) {

        boolean numeric = true;
        numeric = string.matches("-?\\d+(\\.\\d+)?");
        return numeric;

    }

    // Check if the number is numeric
    public static boolean isNumeric(String string) {
        int intValue;

        if (string == null || string.equals("")) {
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

}
