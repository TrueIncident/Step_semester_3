package week9.homework;

import java.util.*;

public class CityCabFareMeter {
    interface NightService {
        double applyNightSurcharge(double fare);
    }

    abstract static class Cab {
        private static final double MINIMUM_FARE = 100.0;
        private final double distanceKm;
        Cab(double distanceKm) { this.distanceKm = distanceKm; }
        double getDistanceKm() { return distanceKm; }
        abstract double ratePerKm();
        abstract String getType();
        final double baseFare() { return Math.max(MINIMUM_FARE, distanceKm * ratePerKm()); }
        final double calculateFare(boolean night) {
            if (night && !(this instanceof NightService)) return -1.0;
            double fare = baseFare();
            return night ? ((NightService) this).applyNightSurcharge(fare) : fare;
        }
    }

    static class Mini extends Cab {
        Mini(double km) { super(km); }
        double ratePerKm() { return 10.0; }
        String getType() { return "MINI"; }
    }
    static class Sedan extends Cab implements NightService {
        Sedan(double km) { super(km); }
        double ratePerKm() { return 14.0; }
        String getType() { return "SEDAN"; }
        public double applyNightSurcharge(double fare) { return fare * 1.20; }
    }
    static class SUV extends Cab implements NightService {
        SUV(double km) { super(km); }
        double ratePerKm() { return 18.0; }
        String getType() { return "SUV"; }
        public double applyNightSurcharge(double fare) { return fare * 1.20; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Cab> cabs = new ArrayList<>();
        List<Boolean> nightTrips = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double km = sc.nextDouble();
            String time = sc.next();
            switch (type) {
                case "MINI": cabs.add(new Mini(km)); break;
                case "SEDAN": cabs.add(new Sedan(km)); break;
                case "SUV": cabs.add(new SUV(km)); break;
                default: throw new IllegalArgumentException("Unknown cab type: " + type);
            }
            nightTrips.add(time.equals("NIGHT"));
        }
        double total = 0;
        for (int i = 0; i < cabs.size(); i++) {
            Cab cab = cabs.get(i);
            double fare = cab.calculateFare(nightTrips.get(i));
            if (fare < 0) {
                System.out.printf("%s: night service not available%n", cab.getType());
            } else {
                System.out.printf("%s: %.2f%n", cab.getType(), fare);
                total += fare;
            }
        }
        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}