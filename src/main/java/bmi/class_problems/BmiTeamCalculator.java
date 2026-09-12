package bmi.class_problems;

import java.util.Random;

public class BmiTeamCalculator {
    static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    static void printWellnessReport(double[] heights, double[] weights) {
        System.out.println("Person | Height (m) | Weight (kg) | BMI  | Status");
        System.out.println("-------------------------------------------------");

        for (int i = 0; i < heights.length; i++) {
            double bmi = weights[i] / (heights[i] * heights[i]);
            String status = getBmiStatus(bmi);
            System.out.printf("%-6d | %-11.2f | %-11.2f | %-4.2f | %s%n",
                    i + 1, heights[i], weights[i], bmi, status);
        }
    }

    public static void main(String[] args) {
        int people = 10;
        double[] heights = new double[people];
        double[] weights = new double[people];
        Random random = new Random();

        for (int i = 0; i < people; i++) {
            heights[i] = 1.50 + (random.nextDouble() * 0.40);
            weights[i] = 45 + (random.nextDouble() * 65);
        }

        printWellnessReport(heights, weights);
    }
}
