package week8.classwork;

import java.util.Scanner;

public class DeliveryFeeCalculator {
    interface Delivery {
        double calculateFee();
    }

    static class StandardDelivery implements Delivery {
        private final double weight;
        private final double distance;

        StandardDelivery(double weight, double distance) {
            this.weight = weight;
            this.distance = distance;
        }

        public double calculateFee() {
            return 5 + 0.50 * weight + 0.10 * distance;
        }
    }

    static class ExpressDelivery implements Delivery {
        private final double weight;
        private final double distance;

        ExpressDelivery(double weight, double distance) {
            this.weight = weight;
            this.distance = distance;
        }

        public double calculateFee() {
            return 15 + 1.00 * weight + 0.20 * distance;
        }
    }

    static class InternationalDelivery implements Delivery {
        private final double weight;
        private final double distance;
        private final double customsFee;

        InternationalDelivery(double weight, double distance, double customsFee) {
            this.weight = weight;
            this.distance = distance;
            this.customsFee = customsFee;
        }

        public double calculateFee() {
            return 25 + 2.00 * weight + 0.50 * distance + customsFee;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double weight = sc.nextDouble();
            double distance = sc.nextDouble();

            Delivery delivery;

            switch (type) {
                case "STANDARD":
                    delivery = new StandardDelivery(weight, distance);
                    break;
                case "EXPRESS":
                    delivery = new ExpressDelivery(weight, distance);
                    break;
                case "INTERNATIONAL":
                    double customsFee = sc.nextDouble();
                    delivery = new InternationalDelivery(weight, distance, customsFee);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid delivery type");
            }

            double fee = delivery.calculateFee();
            System.out.printf("%s: %.2f%n", type, fee);
            total += fee;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
