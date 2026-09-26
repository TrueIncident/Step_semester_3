package week8.classwork;

import java.util.Scanner;

public class PaymentSystemFeeCalculation {
    interface PaymentMethod {
        double calculateFinalAmount(double amount);
    }

    static class Card implements PaymentMethod {
        public double calculateFinalAmount(double amount) {
            return amount * 1.02;
        }
    }

    static class Wallet implements PaymentMethod {
        public double calculateFinalAmount(double amount) {
            return amount * 1.01;
        }
    }

    static class BankTransfer implements PaymentMethod {
        public double calculateFinalAmount(double amount) {
            return amount;
        }
    }

    static PaymentMethod getPaymentMethod(String type) {
        switch (type) {
            case "CARD":
                return new Card();
            case "WALLET":
                return new Wallet();
            case "BANKTRANSFER":
                return new BankTransfer();
            default:
                throw new IllegalArgumentException("Invalid payment type");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double amount = sc.nextDouble();

            PaymentMethod payment = getPaymentMethod(type);
            double adjusted = payment.calculateFinalAmount(amount);

            System.out.printf("%s: %.2f%n", type, adjusted);
            total += adjusted;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
