package week9.classwork;

import java.util.*;

public class LibraryLateFineCounter {
    abstract static class LibraryItem {
        private final String title;
        private final int daysLate;
        LibraryItem(String title, int daysLate) { this.title = title; this.daysLate = daysLate; }
        String getTitle() { return title; }
        int getDaysLate() { return daysLate; }
        abstract double calculateFine();
    }

    static class Book extends LibraryItem {
        Book(String title, int daysLate) { super(title, daysLate); }
        double calculateFine() { return getDaysLate() * 2.0; }
    }

    static class DVD extends LibraryItem {
        DVD(String title, int daysLate) { super(title, daysLate); }
        double calculateFine() { return Math.min(getDaysLate() * 5.0, 50.0); }
    }

    static class Magazine extends LibraryItem {
        Magazine(String title, int daysLate) { super(title, daysLate); }
        double calculateFine() { return getDaysLate(); }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<LibraryItem> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            int days = Integer.parseInt(p[2]);
            switch (p[0]) {
                case "BOOK": items.add(new Book(p[1], days)); break;
                case "DVD": items.add(new DVD(p[1], days)); break;
                case "MAGAZINE": items.add(new Magazine(p[1], days)); break;
                default: throw new IllegalArgumentException("Unknown item type: " + p[0]);
            }
        }
        double total = 0;
        for (LibraryItem item : items) {
            double fine = item.calculateFine();
            System.out.printf("%s: %.2f%n", item.getTitle(), fine);
            total += fine;
        }
        System.out.printf("Total Fines: %.2f%n", total);
        sc.close();
    }
}