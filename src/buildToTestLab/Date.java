package buildToTestLab;
import java.util.*;

public class Date implements Cloneable {
    private String month;
    private int day;
    private int year;

    public Date() {
        this("January", 1, 1000);
    }

    public Date(int monthInt, int day, int year) {
        setDate(monthInt, day, year);
    }

    public Date(String monthString, int day, int year) {
        setDate(monthString, day, year);
    }

    public Date(int year) {
        month = "January";
        day = 1;
        this.year = year;
    }

    public Date(Date aDate) {
        if (aDate == null) {
            System.out.println("Fatal Error in Date(Date).");
            System.exit(0);
        }
        month = aDate.month;
        day = aDate.day;
        year = aDate.year;
    }

    public Date addOneDay() {
        int monthInt = getMonth();
        int daysThisMonth = daysInMonth(monthInt, year);

        if (day < daysThisMonth) {
            day++;
        } else {
            day = 1;
            if (monthInt < 12) {
                setMonth(monthInt + 1);
            } else {
                setMonth(1);
                year++;
            }
        }
        return this;
    }

    public void setDate(int monthInt, int day, int year) {
        if (dateOK(monthInt, day, year) && day <= daysInMonth(monthInt, year)) {
            this.month = monthString(monthInt);
            this.day = day;
            this.year = year;
        } else {
            System.out.println("Fatal Error in setDate(int, int, int)");
            System.exit(0);
        }
    }

    public Date setDate(String monthString, int day, int year) {
        int monthInt = monthStringToInt(monthString);
        if (monthInt == -1 || !dateOK(monthInt, day, year) || day > daysInMonth(monthInt, year)) {
            return null; // Invalid; don't change
        }
        this.month = monthString;
        this.day = day;
        this.year = year;
        return this;
    }

    public void setDate(int year) {
        setDate(1, 1, year);
    }

    public void setYear(int year) {
        if ((year < 1000) || (year > 9999)) {
            System.out.println("Fatal Error in setYear(int)");
            System.exit(0);
        } else {
            this.year = year;
        }
    }

    public void setMonth(int monthNumber) {
        if ((monthNumber <= 0) || (monthNumber > 12)) {
            System.out.println("Fatal Error in setMonth(int)");
            System.exit(0);
        } else {
            month = monthString(monthNumber);
        }
    }

    public void setDay(int day) {
        if ((day <= 0) || (day > 31)) {
            System.out.println("Fatal Error in setDay(int)");
            System.exit(0);
        } else {
            this.day = day;
        }
    }

    public int getMonth() {
        return monthStringToInt(month);
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public String toString() {
        return month + " " + day + ", " + year;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null || !(otherObject instanceof Date)) return false;
        Date otherDate = (Date) otherObject;
        return month.equalsIgnoreCase(otherDate.month) &&
               day == otherDate.day &&
               year == otherDate.year;
    }

    public boolean precedes(Date otherDate) {
        return (year < otherDate.year) ||
               (year == otherDate.year && getMonth() < otherDate.getMonth()) ||
               (year == otherDate.year && getMonth() == otherDate.getMonth() && day < otherDate.day);
    }

    public void readInput() {
        boolean tryAgain = true;
        Scanner keyboard = new Scanner(System.in);
        while (tryAgain) {
            System.out.println("Enter month, day, and year. Do not use a comma.");
            String monthInput = keyboard.next();
            int dayInput = keyboard.nextInt();
            int yearInput = keyboard.nextInt();
            if (setDate(monthInput, dayInput, yearInput) != null) {
                tryAgain = false;
            } else {
                System.out.println("Illegal date. Reenter input.");
            }
        }
    }

    private boolean dateOK(int monthInt, int dayInt, int yearInt) {
        return (monthInt >= 1 && monthInt <= 12 &&
                dayInt >= 1 && dayInt <= 31 &&
                yearInt >= 1000 && yearInt <= 9999);
    }

    private int monthStringToInt(String monthName) {
        String[] months = {"", "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};

        for (int i = 1; i < months.length; i++) {
            if (months[i].equalsIgnoreCase(monthName)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    private int daysInMonth(int month, int year) {
        switch (month) {
            case 2:
                return isLeapYear(year) ? 29 : 28;
            case 4: case 6: case 9: case 11:
                return 30;
            default:
                return 31;
        }
    }

    private String monthString(int monthNumber) {
        switch (monthNumber) {
            case 1:  return "January";
            case 2:  return "February";
            case 3:  return "March";
            case 4:  return "April";
            case 5:  return "May";
            case 6:  return "June";
            case 7:  return "July";
            case 8:  return "August";
            case 9:  return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default:
                System.out.println("Fatal Error in monthString");
                System.exit(0);
                return "Error";
        }
    }

    @Override
    public Object clone() {
        return new Date(this);
    }

    public static void main(String[] args) {
        System.out.println("Main in Date.");
        Date tester = new Date("February", 28, 2024);
        System.out.println("Before: " + tester);
        tester.addOneDay();
        System.out.println("After: " + tester);
    }
}