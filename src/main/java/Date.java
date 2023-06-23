/**
 * A date class.
 */
public class Date {
    private final int day;
    private final int month;
    private final int year;

    private static final int[] thirtyOneDayMonths = new int[] {
        1, 3, 5, 7, 8, 10, 12
    };

    /**
     * Creates a new date.
     *
     * @param day the day in [1,31]
     * @param month the month in [1,12]
     * @param year the year in [1,9999]
     */
    public Date(int day, int month, int year) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException(
                "Day should be in [1,31]");
        }
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException(
                "Month should be in [1,12]");
        }
        if (year < 1 || year > 9999) {
            throw new IllegalArgumentException(
                "Year should be in [1,9999]");
        }
        if (day == 31 && !isThirtyOneDayMonth(month)) {
            throw new IllegalArgumentException(
                String.format(
                    "Month %d does not have 31 days", 
                    month));
        }        
        if (day == 29 && month == 2 && !Date.isLeapYear(year)) {
            throw new IllegalArgumentException(
                "February has 29 days only on leap years");
        }
        if (day > 29 && month == 2) {
            throw new IllegalArgumentException(
                String.format(
                    "Day %d does not exist in February", day)
            );
        }
        
        this.day = day;
        this.month = month;
        this.year = year;
    }

    private static boolean isThirtyOneDayMonth(int month) {
        for (var m : thirtyOneDayMonths) {
            if (month == m) {
                return true;
            }
        }

        return false;
    }

    /**
     * Tests if year is leap year.
     *
     * @param year the year in [1,9999]
     * @return true if is leap year, false otherwise
     */
    public static boolean isLeapYear(int year) {
        if (year < 1 || year > 9999) {
            throw new IllegalArgumentException(
                "Year should be in [1,9999]");
        }
        if (year % 4 != 0) {
            return false;
        }

        if (year % 100 != 0) {
            return true;
        }

        return year % 400 == 0;
    }

    /**
     * Checks if this date is before the parameter. 
     *
     * @param date the other date
     * @return true if is before, false otherwise
     */
    public boolean before(Date date) {
        if (year < date.year) {
            return true;
        }
        if (year == date.year && month < date.month) {
            return true;
        }

        return year == date.year && month == date.month
            && day < date.day;
    }

    /**
     * Checks if this date is after the parameter. 
     *
     * @param date the other date
     * @return true if is after, false otherwise
     */
    public boolean after(Date date) {
        if (year > date.year) {
            return true;
        }
        if (year == date.year && month > date.month) {
            return true;
        }

        return year == date.year && month == date.month
            && day > date.day;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    

    
}
