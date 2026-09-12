package typingaccuracychecker.assigment_problems;

public class TypingSpeedAccuracyChecker {
    static void checkTypingAccuracy(String original, String typed) {
        int total = Math.min(original.length(), typed.length());
        int matched = 0;
        int firstMismatch = -1;

        for (int i = 0; i < total; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatch == -1) {
                firstMismatch = i;
            }
        }

        if (original.length() != typed.length() && firstMismatch == -1) {
            firstMismatch = total;
        }

        int totalCharacters = Math.max(original.length(), typed.length());
        double accuracy = totalCharacters == 0 ? 100.0 : (matched * 100.0) / totalCharacters;

        System.out.printf("Matched: %d/%d | Accuracy: %.2f%%", matched, totalCharacters, accuracy);

        if (firstMismatch == -1) {
            System.out.println(" | No Mismatches");
        } else {
            char originalChar = firstMismatch < original.length() ? original.charAt(firstMismatch) : '-';
            char typedChar = firstMismatch < typed.length() ? typed.charAt(firstMismatch) : '-';
            System.out.println(" | First Mismatch at position " + (firstMismatch + 1) +
                    " ('" + originalChar + "' vs '" + typedChar + "')");
        }
    }

    public static void main(String[] args) {
        String original = "hello world";
        String typed = "hello worlt";
        checkTypingAccuracy(original, typed);
    }
}
