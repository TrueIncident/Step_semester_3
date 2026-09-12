package seatduplicationchecker.assigment_problems;

public class SeatDuplicationChecker {
    static void checkDuplicateSeats(int[] seatNumbers) {
        boolean duplicateFound = false;

        System.out.print("Duplicate Seat Numbers: ");

        for (int i = 0; i < seatNumbers.length; i++) {
            boolean alreadyPrinted = false;

            for (int k = 0; k < i; k++) {
                if (seatNumbers[i] == seatNumbers[k]) {
                    alreadyPrinted = true;
                    break;
                }
            }

            if (alreadyPrinted) {
                continue;
            }

            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    System.out.print(seatNumbers[i] + " ");
                    duplicateFound = true;
                    break;
                }
            }
        }

        if (duplicateFound) {
            System.out.println();
        } else {
            System.out.println("No Duplicate Seats Found");
        }
    }

    public static void main(String[] args) {
        int[] seatNumbers = {101, 102, 103, 102, 105};
        checkDuplicateSeats(seatNumbers);
    }
}
