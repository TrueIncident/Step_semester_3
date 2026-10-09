package week9.homework;

import java.util.*;

public class HomeApplianceEnergyReport {
    interface SaverMode {
        double saverFactor();
    }

    abstract static class Appliance {
        private final double hours;
        Appliance(double hours) { this.hours = hours; }
        double getHours() { return hours; }
        abstract double powerWatts();
        abstract String getName();
        double energyFactor() { return 1.0; }
        final double unitsUsed(boolean saver) {
            return powerWatts() * hours / 1000.0 * (saver ? energyFactor() : 1.0);
        }
        final boolean supportsSaver() { return this instanceof SaverMode; }
    }

    static class Fridge extends Appliance {
        Fridge(double hours) { super(hours); }
        double powerWatts() { return 150.0; }
        String getName() { return "FRIDGE"; }
    }
    static class AC extends Appliance implements SaverMode {
        AC(double hours) { super(hours); }
        double powerWatts() { return 1500.0; }
        String getName() { return "AC"; }
        public double saverFactor() { return 0.75; }
        double energyFactor() { return saverFactor(); }
    }
    static class TV extends Appliance {
        TV(double hours) { super(hours); }
        double powerWatts() { return 100.0; }
        String getName() { return "TV"; }
    }
    static class Washer extends Appliance implements SaverMode {
        Washer(double hours) { super(hours); }
        double powerWatts() { return 500.0; }
        String getName() { return "WASHER"; }
        public double saverFactor() { return 0.75; }
        double energyFactor() { return saverFactor(); }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Appliance> appliances = new ArrayList<>();
        List<Boolean> saverRequests = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double hours = sc.nextDouble();
            boolean saver = sc.hasNext("SAVER");
            if (saver) sc.next();
            Appliance appliance;
            switch (type) {
                case "FRIDGE": appliance = new Fridge(hours); break;
                case "AC": appliance = new AC(hours); break;
                case "TV": appliance = new TV(hours); break;
                case "WASHER": appliance = new Washer(hours); break;
                default: throw new IllegalArgumentException("Unknown appliance: " + type);
            }
            appliances.add(appliance);
            saverRequests.add(saver);
        }
        double totalCost = 0;
        for (int i = 0; i < appliances.size(); i++) {
            Appliance appliance = appliances.get(i);
            boolean saver = saverRequests.get(i);
            if (saver && !appliance.supportsSaver()) {
                System.out.printf("%s: saver mode not supported%n", appliance.getName());
                continue;
            }
            double units = appliance.unitsUsed(saver);
            double cost = units * 8.0;
            System.out.printf("%s: Units=%.2f Cost=%.2f%n", appliance.getName(), units, cost);
            totalCost += cost;
        }
        System.out.printf("Total Cost: %.2f%n", totalCost);
        sc.close();
    }
}