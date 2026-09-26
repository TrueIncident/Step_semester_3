package week8.homework;

import java.time.LocalDate;
import java.util.*;
import java.time.format.DateTimeFormatter;

public class StreamingPlanRenewalReminder {
    interface Plan {
        LocalDate renewalDate(LocalDate startDate);
        String name();
    }

    static class Basic implements Plan {
        public LocalDate renewalDate(LocalDate startDate) { return startDate.plusDays(30); }
        public String name() { return "BASIC"; }
    }

    static class Standard implements Plan {
        public LocalDate renewalDate(LocalDate startDate) { return startDate.plusDays(90); }
        public String name() { return "STANDARD"; }
    }

    static class Premium implements Plan {
        public LocalDate renewalDate(LocalDate startDate) { return startDate.plusDays(365); }
        public String name() { return "PREMIUM"; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String subscriberName = sc.next();
            LocalDate startDate = LocalDate.parse(sc.next(), formatter);
            Plan plan;

            switch (type) {
                case "BASIC" -> plan = new Basic();
                case "STANDARD" -> plan = new Standard();
                default -> plan = new Premium();
            }

            System.out.printf("%s: %s%n",
                    subscriberName, plan.renewalDate(startDate).format(formatter));
        }
    }
}
