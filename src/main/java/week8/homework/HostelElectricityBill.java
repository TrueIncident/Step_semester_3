package week8.homework;

import java.util.*;

public class HostelElectricityBill {
    interface Room {
        double bill(int units);
        String type();
    }

    static class SingleRoom implements Room {
        public double bill(int units) { return units * 8.0; }
        public String type() { return "SINGLE"; }
    }

    static class SharedRoom implements Room {
        private final int occupants;

        SharedRoom(int occupants) { this.occupants = occupants; }

        public double bill(int units) {
            return units * 6.0 / occupants;
        }

        public String type() { return "SHARED"; }
    }

    static class AcRoom implements Room {
        public double bill(int units) { return units * 10.0 + 200.0; }
        public String type() { return "AC"; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            int units = sc.nextInt();
            Room room;

            if (type.equals("SHARED")) {
                int occupants = sc.nextInt();
                room = new SharedRoom(occupants);
            } else if (type.equals("SINGLE")) {
                room = new SingleRoom();
            } else {
                room = new AcRoom();
            }

            double bill = room.bill(units);
            System.out.printf("%s: %.2f%n", room.type(), bill);
            total += bill;
        }

        System.out.printf("Total: %.2f%n", total);
    }
}
