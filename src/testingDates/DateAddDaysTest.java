package testingDates;
import org.junit.*;
import buildToTestLab.Date;
import static org.junit.Assert.*;
public class DateAddDaysTest {

    @Test
    public void testAddOneDaySameMonthEarlyMonth() {
        Date date = new Date(3, 14, 2023);
        date.addOneDay();
        assertEquals("3/15/2023", date.toString());
    }

    @Test
    public void testAddOneDaySameMonthEndOfMonth() {
        Date date = new Date(6, 29, 2023);
        date.addOneDay();
        assertEquals("6/30/2023", date.toString());
    }

    @Test
    public void testAddOneDayMonthBoundaryApril() {
        Date date = new Date(4, 30, 2023);
        date.addOneDay();
        assertEquals("5/1/2023", date.toString());
    }

    @Test
    public void testAddOneDayMonthBoundaryFebruaryNonLeap() {
        Date date = new Date(2, 28, 2023); // non-leap year - Feb 28
        date.addOneDay();
        assertEquals("3/1/2023", date.toString());
    }

    @Test
    public void testAddOneDayMonthBoundaryDecember() {
        Date date = new Date(12, 31, 2023);
        date.addOneDay();
        assertEquals("1/1/2024", date.toString());
    }

    @Test
    public void testAddOneDayFebruaryLeapYear() {
        Date date = new Date(2, 28, 2024); // leap year - Feb 28
        date.addOneDay();
        assertEquals("2/29/2024", date.toString());
    }

    @Test
    public void testAddOneDayEndOfLeapFebruary() {
        Date date = new Date(2, 29, 2024);
        date.addOneDay();
        assertEquals("3/1/2024", date.toString());
    }
}