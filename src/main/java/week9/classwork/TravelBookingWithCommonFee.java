package week9.classwork;

import java.util.*;

public class TravelBookingWithCommonFee {
    abstract static class Booking {
        private static final double BOOKING_FEE = 50.0;
        private final double distanceKm;
        Booking(double distanceKm) { this.distanceKm = distanceKm; }
        double getDistanceKm() { return distanceKm; }
        abstract double calculateBaseFare();
        abstract String getMode();
        final double calculateTotal() { return calculateBaseFare() + BOOKING_FEE; }
    }

    static class Bus extends Booking {
        Bus(double distanceKm) { super(distanceKm); }
        double calculateBaseFare() { return getDistanceKm() * 2.0; }
        String getMode() { return "BUS"; }
    }

    static class Train extends Booking {
        Train(double distanceKm) { super(distanceKm); }
        double calculateBaseFare() { return getDistanceKm() * 1.5; }
        String getMode() { return "TRAIN"; }
    }

    static class Flight extends Booking {
        Flight(double distanceKm) { super(distanceKm); }
        double calculateBaseFare() { return 2500.0 + getDistanceKm() * 4.0; }
        String getMode() { return "FLIGHT"; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Booking> bookings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            double distance = Double.parseDouble(p[1]);
            switch (p[0]) {
                case "BUS": bookings.add(new Bus(distance)); break;
                case "TRAIN": bookings.add(new Train(distance)); break;
                case "FLIGHT": bookings.add(new Flight(distance)); break;
                default: throw new IllegalArgumentException("Unknown travel mode: " + p[0]);
            }
        }
        for (Booking booking : bookings) {
            System.out.printf("%s: %.2f%n", booking.getMode(), booking.calculateTotal());
        }
        sc.close();
    }
}