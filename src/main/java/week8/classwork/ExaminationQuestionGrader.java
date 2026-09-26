package week8.classwork;

import java.util.Scanner;

public class ExaminationQuestionGrader {
    interface Question {
        double grade();
        String getType();
    }

    static class MCQ implements Question {
        private final String correct;
        private final String student;
        private final double points;

        MCQ(String correct, String student, double points) {
            this.correct = correct;
            this.student = student;
            this.points = points;
        }

        public double grade() {
            return student.equalsIgnoreCase(correct) ? points : 0;
        }

        public String getType() {
            return "MCQ";
        }
    }

    static class TF implements Question {
        private final String correct;
        private final String student;
        private final double points;

        TF(String correct, String student, double points) {
            this.correct = correct;
            this.student = student;
            this.points = points;
        }

        public double grade() {
            return student.equalsIgnoreCase(correct) ? points : 0;
        }

        public String getType() {
            return "TF";
        }
    }

    static class Essay implements Question {
        private final String correctKeywords;
        private final String student;
        private final double points;

        Essay(String correctKeywords, String student, double points) {
            this.correctKeywords = correctKeywords;
            this.student = student;
            this.points = points;
        }

        public double grade() {
            String studentLower = student.toLowerCase();
            int matches = 0;

            for (String keyword : correctKeywords.split(",")) {
                if (studentLower.contains(keyword.trim().toLowerCase())) {
                    matches++;
                }
            }

            if (matches >= 2) {
                return points * 0.75;
            } else if (matches == 1) {
                return points * 0.50;
            }

            return 0;
        }

        public String getType() {
            return "ESSAY";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String[] parts = line.split(""");

            String type = parts[0].trim();
            String correct = parts[2].trim();
            String student = parts[4].trim();
            double points = Double.parseDouble(parts[5].trim());

            Question question;

            switch (type) {
                case "MCQ":
                    question = new MCQ(correct, student, points);
                    break;
                case "TF":
                    question = new TF(correct, student, points);
                    break;
                case "ESSAY":
                    question = new Essay(correct, student, points);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid question type");
            }

            double score = question.grade();
            System.out.printf("%s: %.2f%n", question.getType(), score);
            total += score;
        }

        System.out.printf("Total Score: %.2f%n", total);
        sc.close();
    }
}
