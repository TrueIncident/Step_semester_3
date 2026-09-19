package student.class_problems;

public class Student {
    String name;
    double attendance;
    static String collegeName = "SRM Institute of Science and Technology";
    static int studentCount = 0;

    public Student(String name, double attendance) {
        this.name = name;
        this.attendance = attendance;
        studentCount++;
    }

    static void printCollegeInfo() {
        System.out.println(collegeName + " Students created: " + studentCount);
    }

    public static void main(String[] args) {
        new Student("Ravi", 90.0);
        new Student("Anitha", 95.0);

        Student.printCollegeInfo();
    }
}