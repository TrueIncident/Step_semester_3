package week9.homework;

import java.util.*;

public class ParcelShippingDesk {
    interface Insurable {
        double calculateInsurance();
    }

    abstract static class Parcel {
        private final double weightKg;
        private final double declaredValue;
        Parcel(double weightKg, double declaredValue) {
            this.weightKg = weightKg;
            this.declaredValue = declaredValue;
        }
        double getWeightKg() { return weightKg; }
        double getDeclaredValue() { return declaredValue; }
        abstract double calculateCharge();
        abstract String getType();
        double calculateInsurance() { return 0.0; }
        final double calculateTotal() { return calculateCharge() + calculateInsurance(); }
    }

    static class Standard extends Parcel {
        Standard(double weight, double value) { super(weight, value); }
        double calculateCharge() { return 40.0 + 10.0 * getWeightKg(); }
        String getType() { return "STANDARD"; }
    }

    static class Express extends Parcel implements Insurable {
        Express(double weight, double value) { super(weight, value); }
        double calculateCharge() { return 80.0 + 15.0 * getWeightKg(); }
        public double calculateInsurance() { return 0.02 * getDeclaredValue(); }
        String getType() { return "EXPRESS"; }
    }

    static class Fragile extends Parcel implements Insurable {
        Fragile(double weight, double value) { super(weight, value); }
        double calculateCharge() { return 40.0 + 10.0 * getWeightKg() + 50.0; }
        public double calculateInsurance() { return 0.02 * getDeclaredValue(); }
        String getType() { return "FRAGILE"; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Parcel> parcels = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double weight = sc.nextDouble();
            double value = sc.nextDouble();
            switch (type) {
                case "STANDARD": parcels.add(new Standard(weight, value)); break;
                case "EXPRESS": parcels.add(new Express(weight, value)); break;
                case "FRAGILE": parcels.add(new Fragile(weight, value)); break;
                default: throw new IllegalArgumentException("Unknown parcel type: " + type);
            }
        }
        double grandTotal = 0;
        for (Parcel parcel : parcels) {
            double charge = parcel.calculateCharge();
            double insurance = parcel.calculateInsurance();
            double total = charge + insurance;
            System.out.printf("%s: Charge=%.2f Insurance=%.2f Total=%.2f%n",
                    parcel.getType(), charge, insurance, total);
            grandTotal += total;
        }
        System.out.printf("Grand Total: %.2f%n", grandTotal);
        sc.close();
    }
}