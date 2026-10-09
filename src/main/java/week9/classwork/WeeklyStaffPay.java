package week9.classwork;

import java.util.*;

public class WeeklyStaffPay {
    abstract static class Staff {
        private final String name;
        Staff(String name) { this.name = name; }
        String getName() { return name; }
        abstract double calculatePay();
    }

    static class FullTimeStaff extends Staff {
        private final double weeklySalary;
        FullTimeStaff(String name, double weeklySalary) {
            super(name); this.weeklySalary = weeklySalary;
        }
        double calculatePay() { return weeklySalary; }
    }

    static class HourlyStaff extends Staff {
        private final double hours, rate;
        HourlyStaff(String name, double hours, double rate) {
            super(name); this.hours = hours; this.rate = rate;
        }
        double calculatePay() {
            return Math.min(hours, 40) * rate + Math.max(0, hours - 40) * rate * 1.5;
        }
    }

    static class Intern extends Staff {
        private final double stipend;
        Intern(String name, double stipend) { super(name); this.stipend = stipend; }
        double calculatePay() { return stipend; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Staff> staffList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            switch (p[0]) {
                case "FULLTIME": staffList.add(new FullTimeStaff(p[1], Double.parseDouble(p[2]))); break;
                case "HOURLY": staffList.add(new HourlyStaff(p[1], Double.parseDouble(p[2]), Double.parseDouble(p[3]))); break;
                case "INTERN": staffList.add(new Intern(p[1], Double.parseDouble(p[2]))); break;
                default: throw new IllegalArgumentException("Unknown staff type: " + p[0]);
            }
        }
        double total = 0;
        for (Staff staff : staffList) {
            double pay = staff.calculatePay();
            System.out.printf("%s: %.2f%n", staff.getName(), pay);
            total += pay;
        }
        System.out.printf("Total Payroll: %.2f%n", total);
        sc.close();
    }
}