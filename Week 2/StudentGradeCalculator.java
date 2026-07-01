import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {

        // Create Scanner object
        Scanner sc = new Scanner(System.in);

        int numberOfSubjects;

        // -------- INPUT PHASE --------
        // Get number of subjects with validation
        while (true) {
            System.out.print("Enter number of subjects: ");
            if (sc.hasNextInt()) {
                numberOfSubjects = sc.nextInt();
                if (numberOfSubjects > 0) {
                    break;
                } else {
                    System.out.println("Number of subjects must be greater than 0.");
                }
            } else {
                System.out.println("Please enter a valid integer.");
                sc.nextLine(); // clear invalid input
            }
        }

        int totalMarks = 0;

        // Loop to take marks for each subject
        for (int i = 1; i <= numberOfSubjects; i++) {
            int mark;

            while (true) {
                System.out.print("Enter marks for subject " + i + " (0-100): ");

                // Check if input is integer
                if (sc.hasNextInt()) {
                    mark = sc.nextInt();

                    // Validate range
                    if (mark >= 0 && mark <= 100) {
                        break;
                    } else {
                        System.out.println("Marks must be between 0 and 100.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.nextLine(); // clear invalid input
                }
            }

            totalMarks += mark; // Accumulate total
        }

        // -------- PROCESS PHASE --------

        // Calculate average using type casting
        double average = (double) totalMarks / numberOfSubjects;

        // Assign grade using conditional logic
        char grade;

        if (average >= 90) {
            grade = 'A';
        } else if (average >= 80) {
            grade = 'B';
        } else if (average >= 70) {
            grade = 'C';
        } else if (average >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // -------- OUTPUT PHASE --------

        System.out.println("\n----- RESULT -----");
        System.out.println("Total Marks: " + totalMarks);

        // formatted output
        System.out.printf("Average Percentage: %.2f%%\n", average);
        System.out.println("Grade: " + grade);

        sc.close();
    }
}
