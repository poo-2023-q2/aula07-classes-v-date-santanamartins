import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for the date class.
 */
public class DateTest {

    private static final String validDates = "src/test/resources/dates-ok.csv";
    private static final String invalidDates = "src/test/resources/dates-fail.csv";
    private static final String leapYears = "src/test/resources/leap-years.csv";
    private static final String nonLeapYears = "src/test/resources/non-leap-years.csv";

    @Test
    public void testSuccessOnConstructor() {
        try (var scanner = new Scanner(new File(validDates))) {
            var line = scanner.next();
            var datePieces = line.split("/");
            var day = Integer.parseInt(datePieces[0]);
            var month = Integer.parseInt(datePieces[1]);
            var year = Integer.parseInt(datePieces[2]);

            Assertions.assertDoesNotThrow(() -> new Date(day, month, year),
                String.format("Date %s is a valid date, creation should not throw exception.",
                    line));
        } catch (Exception e) {
            Assertions.fail("Failed to parse success case file", e);
        }
    }

    @Test
    public void testFailureOnConstructor() {
        try (var scanner = new Scanner(new File(invalidDates))) {
            var line = scanner.next();
            var datePieces = line.split("/");
            var day = Integer.parseInt(datePieces[0]);
            var month = Integer.parseInt(datePieces[1]);
            var year = Integer.parseInt(datePieces[2]);

            Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Date(day, month, year),
                String.format("Date %s is an invalid date, should throw IllegalArgumentException",
                    line));
        } catch (Exception e) {
            Assertions.fail("Failed to parse failure cases file", e);
        }
    }

    @Test
    public void testLeapYearSuccess() {
        try (var scanner = new Scanner(new File(leapYears))) {
            var year = scanner.nextInt();

            Assertions.assertTrue(Date.isLeapYear(year),
                String.format("Year %d is a leap year.", year));
        } catch (Exception e) {
            Assertions.fail("Failed to parse leap years file", e);
        }
    }

    @Test
    public void testNonLeapYearSuccess() {
        try (var scanner = new Scanner(new File(nonLeapYears))) {
            var year = scanner.nextInt();

            Assertions.assertFalse(Date.isLeapYear(year),
                String.format("Year %d is not a leap year.", year));
        } catch (Exception e) {
            Assertions.fail("Failed to parse non-leap years file", e);
        }
    }

    private List<Date> loadAllValidDates() {
        var dates = new ArrayList<Date>();

        try (var scanner = new Scanner(new File(validDates))) {
            while (scanner.hasNext()) {
                var line = scanner.next();
                var datePieces = line.split("/");
                var day = Integer.parseInt(datePieces[0]);
                var month = Integer.parseInt(datePieces[1]);
                var year = Integer.parseInt(datePieces[2]);

                dates.add(new Date(day, month, year));
            }
        } catch (Exception e) {
            Assertions.fail("Failed to parse success case file", e);
        }

        return dates;
    }

    @Test
    public void testIsBefore() {
        var dates = loadAllValidDates();

        for (var i = 1; i < dates.size(); i++) {
            var d1 = dates.get(i - 1);
            var d2 = dates.get(i);

            Assertions.assertTrue(d1.before(d2), String.format("Date %s is before %s", d1, d2));
        }

        var date = new Date(1, 1, 2022);

        Assertions.assertFalse(date.before(date), "A date is never before itself");
    }

    @Test
    public void testIsAfter() {
        var dates = loadAllValidDates();

        for (var i = dates.size() - 1; i > 0; i--) {
            var d1 = dates.get(i);
            var d2 = dates.get(i - 1);

            Assertions.assertTrue(d1.after(d2), String.format("Date %s is after %s", d1, d2));
        }

        var date = new Date(1, 1, 2022);

        Assertions.assertFalse(date.after(date), "A date is never after itself");
    }
}
