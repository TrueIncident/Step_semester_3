package week8.homework;

import java.util.*;

public class CampusParkingChargeCalculator {
    interface Vehicle {
        double charge(int hours);
        String type();
    }

    static class Bike implements Vehicle {
        public double charge(int hours) { return hours * 10.0; }
        public String type() { return "BIKE"; }
    }

    static class Car implements Vehicle {
        public double charge(int hours) {
            return 30.0 + (hours - 1) * 20.0;
        }
        public String type() { return "CAR"; }
    }

    static class Truck implements Vehicle {
        public double charge(int hours) { return Math.max(100.0, hours * 50.0); }
        public String type() { return "TRUCK"; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            int hours = sc.nextInt();
            Vehicle vehicle;

            switch (type) {
                case "BIKE" -> vehicle = new Bike();
                case "CAR" -> vehicle = new Car();
                default -> vehicle = new Truck();
            }

            double charge = vehicle.charge(hours);
            System.out.printf("%s: %.2f%n", vehicle.type(), charge);
            total += charge;
        }

        System.out.printf("Total: %.2f%n", total);
    }
}
