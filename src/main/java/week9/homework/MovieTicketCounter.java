package week9.homework;

import java.util.*;

public class MovieTicketCounter {
    abstract static class Ticket {
        private static final double CONVENIENCE_FEE = 20.0;
        private final int count;
        Ticket(int count) { this.count = count; }
        int getCount() { return count; }
        abstract double pricePerTicket();
        abstract String getSeat();
        final double totalAmount() { return count * (pricePerTicket() + CONVENIENCE_FEE); }
    }

    static class Regular extends Ticket {
        Regular(int count) { super(count); }
        double pricePerTicket() { return 150.0; }
        String getSeat() { return "REGULAR"; }
    }
    static class Premium extends Ticket {
        Premium(int count) { super(count); }
        double pricePerTicket() { return 250.0; }
        String getSeat() { return "PREMIUM"; }
    }
    static class Recliner extends Ticket {
        Recliner(int count) { super(count); }
        double pricePerTicket() { return 400.0; }
        String getSeat() { return "RECLINER"; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String seat = sc.next();
            int count = sc.nextInt();
            switch (seat) {
                case "REGULAR": tickets.add(new Regular(count)); break;
                case "PREMIUM": tickets.add(new Premium(count)); break;
                case "RECLINER": tickets.add(new Recliner(count)); break;
                default: throw new IllegalArgumentException("Unknown seat type: " + seat);
            }
        }
        double total = 0;
        for (Ticket ticket : tickets) {
            double amount = ticket.totalAmount();
            System.out.printf("%s: %.2f%n", ticket.getSeat(), amount);
            total += amount;
        }
        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}