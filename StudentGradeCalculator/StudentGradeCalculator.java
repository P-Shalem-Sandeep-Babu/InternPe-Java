package StudentGradeCalculator;
import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine(); 

        for (int i = 1; i <= numStudents; i++) {
            System.out.println("\nStudent " + i);

            System.out.print("Enter the student's name: ");
            String name = scanner.nextLine();

            System.out.print("Enter the number of subjects for " + name + ": ");
            int numSubjects = scanner.nextInt();

            int totalMarks = 0;

            for (int j = 1; j <= numSubjects; j++) {
                System.out.print("Enter marks for Subject " + j + " (out of 100): ");
                int marks = scanner.nextInt();
                totalMarks += marks;
            }

            float percentage = (float) totalMarks / (numSubjects * 100) * 100;

            System.out.println("\nSummary for " + name + ":");
            System.out.println("Total Marks: " + totalMarks + " out of " + (numSubjects * 100));
            System.out.printf("Percentage: %.2f%%\n", percentage);

            scanner.nextLine(); 
        }

        scanner.close();
    }
}
