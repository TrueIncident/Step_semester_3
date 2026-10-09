package week9.homework;

import java.util.*;

public class CollegeFeeCounter {
    interface BusUser {
        double transportFee();
    }

    abstract static class Student {
        private final String name;
        Student(String name) { this.name = name; }
        String getName() { return name; }
        abstract double tuitionFee();
        double hostelFee() { return 0.0; }
        final double totalFee() { return tuitionFee() + hostelFee() + (this instanceof BusUser ? ((BusUser) this).transportFee() : 0.0); }
    }

    static class DayScholar extends Student implements BusUser {
        DayScholar(String name) { super(name); }
        double tuitionFee() { return 40000.0; }
        public double transportFee() { return 12000.0; }
    }

    static class Hosteller extends Student {
        Hosteller(String name) { super(name); }
        double tuitionFee() { return 40000.0; }
        double hostelFee() { return 60000.0; }
    }

    static class Scholar extends Student implements BusUser {
        Scholar(String name) { super(name); }
        double tuitionFee() { return 20000.0; }
        public double transportFee() { return 12000.0; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            switch (type) {
                case "DAY_SCHOLAR": students.add(new DayScholar(name)); break;
                case "HOSTELLER": students.add(new Hosteller(name)); break;
                case "SCHOLAR": students.add(new Scholar(name)); break;
                default: throw new IllegalArgumentException("Unknown student type: " + type);
            }
        }
        double total = 0;
        for (Student student : students) {
            double fee = student.totalFee();
            System.out.printf("%s: %.2f%n", student.getName(), fee);
            total += fee;
        }
        System.out.printf("Total Collected: %.2f%n", total);
        sc.close();
    }
}