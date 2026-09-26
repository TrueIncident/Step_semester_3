package week8.homework;

import java.util.*;

public class FestivalBonusCalculator {
    interface Employee {
        double bonus();
        String name();
    }

    static class FullTime implements Employee {
        private final String employeeName;
        private final double salary;

        FullTime(String employeeName, double salary) {
            this.employeeName = employeeName;
            this.salary = salary;
        }

        public double bonus() { return salary * 0.10; }
        public String name() { return employeeName; }
    }

    static class PartTime implements Employee {
        private final String employeeName;
        private final double salary;

        PartTime(String employeeName, double salary) {
            this.employeeName = employeeName;
            this.salary = salary;
        }

        public double bonus() { return salary * 0.05; }
        public String name() { return employeeName; }
    }

    static class Intern implements Employee {
        private final String employeeName;

        Intern(String employeeName, double salary) {
            this.employeeName = employeeName;
        }

        public double bonus() { return 2000.0; }
        public String name() { return employeeName; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            double salary = sc.nextDouble();
            Employee employee;

            switch (type) {
                case "FULLTIME" -> employee = new FullTime(name, salary);
                case "PARTTIME" -> employee = new PartTime(name, salary);
                default -> employee = new Intern(name, salary);
            }

            double bonus = employee.bonus();
            System.out.printf("%s: %.2f%n", employee.name(), bonus);
            total += bonus;
        }

        System.out.printf("Total Bonus: %.2f%n", total);
    }
}
