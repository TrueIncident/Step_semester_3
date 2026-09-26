package week8.homework;

import java.util.*;

public class CanteenBillingCounter {
    interface Customer {
        double finalAmount(double amount);
        String type();
    }

    static class Student implements Customer {
        public double finalAmount(double amount) { return amount * 0.90; }
        public String type() { return "STUDENT"; }
    }

    static class Staff implements Customer {
        public double finalAmount(double amount) { return amount * 0.95; }
        public String type() { return "STAFF"; }
    }

    static class Guest implements Customer {
        public double finalAmount(double amount) { return amount + 10; }
        public String type() { return "GUEST"; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double amount = sc.nextDouble();
            Customer customer;

            switch (type) {
                case "STUDENT" -> customer = new Student();
                case "STAFF" -> customer = new Staff();
                default -> customer = new Guest();
            }

            double finalAmount = customer.finalAmount(amount);
            System.out.printf("%s: %.2f%n", customer.type(), finalAmount);
            total += finalAmount;
        }

        System.out.printf("Total: %.2f%n", total);
    }
}
