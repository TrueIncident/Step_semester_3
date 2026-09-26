package week8.classwork;

import java.time.LocalDate;
import java.util.Scanner;

public class LibraryItemDueDateCalculator {
    interface LibraryItem {
        LocalDate calculateDueDate(LocalDate currentDate);
    }

    static class Book implements LibraryItem {
        private final String title;
        Book(String title) {
            this.title = title;
        }
        public LocalDate calculateDueDate(LocalDate currentDate) {
            return currentDate.plusDays(14);
        }
        String getTitle() {
            return title;
        }
    }

    static class DVD implements LibraryItem {
        private final String title;
        DVD(String title) {
            this.title = title;
        }
        public LocalDate calculateDueDate(LocalDate currentDate) {
            return currentDate.plusDays(7);
        }
        String getTitle() {
            return title;
        }
    }

    static class Magazine implements LibraryItem {
        private final String title;
        Magazine(String title) {
            this.title = title;
        }
        public LocalDate calculateDueDate(LocalDate currentDate) {
            return currentDate.plusDays(3);
        }
        String getTitle() {
            return title;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LocalDate currentDate = LocalDate.of(2023, 10, 26);
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            int firstSpace = line.indexOf(' ');
            String type = line.substring(0, firstSpace);
            String title = line.substring(firstSpace + 1).replace(""", "");

            LibraryItem item;

            switch (type) {
                case "BOOK":
                    item = new Book(title);
                    break;
                case "DVD":
                    item = new DVD(title);
                    break;
                case "MAGAZINE":
                    item = new Magazine(title);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid item type");
            }

            LocalDate dueDate = item.calculateDueDate(currentDate);
            System.out.println(title + ": " + dueDate);
        }

        sc.close();
    }
}
