package week9.classwork;

import java.util.*;

public class GardenPlotAreaReport {
    abstract static class Plot {
        private final String owner;
        Plot(String owner) { this.owner = owner; }
        String getOwner() { return owner; }
        abstract double area();
        abstract String shape();
    }

    static class Circle extends Plot {
        private final double radius;
        Circle(String owner, double radius) { super(owner); this.radius = radius; }
        double area() { return Math.PI * radius * radius; }
        String shape() { return "CIRCLE"; }
    }

    static class Rectangle extends Plot {
        private final double length, width;
        Rectangle(String owner, double length, double width) {
            super(owner); this.length = length; this.width = width;
        }
        double area() { return length * width; }
        String shape() { return "RECTANGLE"; }
    }

    static class Triangle extends Plot {
        private final double base, height;
        Triangle(String owner, double base, double height) {
            super(owner); this.base = base; this.height = height;
        }
        double area() { return 0.5 * base * height; }
        String shape() { return "TRIANGLE"; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Plot> plots = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            switch (p[0]) {
                case "CIRCLE": plots.add(new Circle(p[1], Double.parseDouble(p[2]))); break;
                case "RECTANGLE": plots.add(new Rectangle(p[1], Double.parseDouble(p[2]), Double.parseDouble(p[3]))); break;
                case "TRIANGLE": plots.add(new Triangle(p[1], Double.parseDouble(p[2]), Double.parseDouble(p[3]))); break;
                default: throw new IllegalArgumentException("Unknown shape: " + p[0]);
            }
        }
        double total = 0;
        for (Plot plot : plots) {
            double area = plot.area();
            System.out.printf("%s (%s): %.2f%n", plot.getOwner(), plot.shape(), area);
            total += area;
        }
        System.out.printf("Total Area: %.2f%n", total);
        sc.close();
    }
}