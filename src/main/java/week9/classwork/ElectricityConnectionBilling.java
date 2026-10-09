package week9.classwork;

import java.util.*;

public class ElectricityConnectionBilling {
    abstract static class Connection {
        private final int units;
        Connection(int units) { this.units = units; }
        int getUnits() { return units; }
        abstract double calculateBill();
        abstract String getType();
    }

    static class Home extends Connection {
        Home(int units) { super(units); }
        double calculateBill() {
            return Math.min(getUnits(), 100) * 5.0 + Math.max(0, getUnits() - 100) * 7.0;
        }
        String getType() { return "HOME"; }
    }

    static class Shop extends Connection {
        Shop(int units) { super(units); }
        double calculateBill() { return getUnits() * 8.0 + 100.0; }
        String getType() { return "SHOP"; }
    }

    static class Factory extends Connection {
        Factory(int units) { super(units); }
        double calculateBill() { return Math.max(getUnits() * 6.0, 1000.0); }
        String getType() { return "FACTORY"; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Connection> connections = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            int units = Integer.parseInt(p[1]);
            switch (p[0]) {
                case "HOME": connections.add(new Home(units)); break;
                case "SHOP": connections.add(new Shop(units)); break;
                case "FACTORY": connections.add(new Factory(units)); break;
                default: throw new IllegalArgumentException("Unknown connection type: " + p[0]);
            }
        }
        double total = 0;
        for (Connection connection : connections) {
            double bill = connection.calculateBill();
            System.out.printf("%s: %.2f%n", connection.getType(), bill);
            total += bill;
        }
        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}