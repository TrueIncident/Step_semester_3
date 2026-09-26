package week7.classwork;

public class Locker {
    private String combination;
    private final int lockerNumber;

    public Locker(int lockerNumber, String combination) {
        this.lockerNumber = lockerNumber;
        this.combination = combination;
    }

    public void changeCode(String currentCode, String newCode) {
        if (combination.equals(currentCode)) {
            combination = newCode;
            System.out.println("Code changed successfully");
        } else {
            System.out.println("Code change rejected");
        }
    }

    public static void main(String[] args) {
        Locker locker = new Locker(101, "1234");

        locker.changeCode("1234", "5678");
        locker.changeCode("0000", "9999");
    }
}
