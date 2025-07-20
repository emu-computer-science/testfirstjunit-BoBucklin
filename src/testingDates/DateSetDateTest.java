package testingDates;

import org.junit.*;
import buildToTestLab.Date;
import static org.junit.Assert.*;

public class DateSetDateTest {

    @Test
    public void testSetDateLegal() {
        Date d = new Date(1, 1, 2020);
        d.setDate("March", 15, 2023);
        assertEquals("March 15, 2023", d.toString());
    }

    @Test
    public void testSetDateIllegalMonthName() {
        Date d = new Date(5, 10, 2023);
        d.setDate("Marrch", 15, 2023);  // misspelled month
        assertEquals("May 10, 2023", d.toString()); // unchanged
    }

    @Test
    public void testSetDateIllegalDay() {
        Date d = new Date(5, 10, 2023);
        d.setDate("June", 31, 2023); // June has only 30 days
        assertEquals("May 10, 2023", d.toString()); // unchanged
    }

    @Test
    public void testSetDateFebruaryLeapYear() {
        Date d = new Date(1, 1, 2020);
        d.setDate("February", 29, 2024); // legal leap day
        assertEquals("February 29, 2024", d.toString());
    }

    @Test
    public void testSetDateFebruaryNonLeapYear() {
        Date d = new Date(1, 1, 2020);
        d.setDate("February", 29, 2023); // illegal, 2023 is not leap year
        assertEquals("January 1, 2020", d.toString()); // unchanged
    }

    @Test
    public void testSetDateLowerCaseMonth() {
        Date d = new Date(1, 1, 2020);
        d.setDate("january", 10, 2025);
        assertEquals("january 10, 2025", d.toString());  // Month case preserved
    }

    @Test
    public void testSetDateMixedCaseMonth() {
        Date d = new Date(1, 1, 2020);
        d.setDate("aPrIL", 25, 2025);
        assertEquals("aPrIL 25, 2025", d.toString());  // Month case preserved
    }
}