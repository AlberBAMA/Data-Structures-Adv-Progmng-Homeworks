import java.util.Scanner;

public class MethodsFunctionsAssignment {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Variable for control the while loop Alber Baez
        boolean whileLoopControl = true;

        while (whileLoopControl) {
            System.out.println("=== New Calculation ===");

            // Enter and validation of numbers greater than 0
            double number1 = validatePositiveNumber(scanner, "Type the first number (greater than 0): ");
            double number2 = validatePositiveNumber(scanner, "Type the second number (greater than 0): ");

            boolean continueToMenu = true;

            // Loop that is going to show the menu until the user decide to change the numbers or leave.
            while (continueToMenu) {

                showMenu();
                int option = scanner.nextInt();

                if (option == 1) {
                    System.out.println("Numbers: " + number1 + " and " + number2 + " | Result of the sum: " + add(number1, number2));
                } else if (option == 2) {
                    System.out.println("Numbers: " + number1 + " and " + number2 + " | Result of the subtraction: " + subs(number1, number2));
                } else if (option == 3) {
                    System.out.println("Numbers: " + number1 + " and " + number2 + " | Result of the multiplication: " + multiply(number1, number2));
                } else if (option == 4) {
                    System.out.println("Numbers: " + number1 + " and " + number2 + " | Result of the division: " + divide(number1, number2));
                } else if (option == 5) {

                    double addition = add(number1, number2);
                    double subtraction = subs(number1, number2);
                    double multiplication = multiply(number1, number2);
                    double division = divide(number1, number2);
                    showAllResults(number1, number2, addition, subtraction, multiplication, division);

                } else if (option == 6) {
                    continueToMenu = false; // Reset to ask for new numbers
                } else if (option == 7) {
                    continueToMenu = false;
                    whileLoopControl = false; // State the variable as false to eave the program
                    System.out.println("You left the program!");
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
                System.out.println();
            }
        }
        scanner.close();
    }

    // Method for validate the 2 positive numbers.
    public static double validatePositiveNumber(Scanner scanner, String instruction) {
        double number;
        do {
            System.out.print(instruction);
            number = scanner.nextDouble();
            if (number <= 0) {
                System.out.println("Error: Type a number greater than 0.");
            }
        } while (number <= 0);
        return number;
    }

    // Method for show the menu
    public static void showMenu() {
        System.out.println("----- Options Menu -----");
        System.out.println("1. Add the two numbers");
        System.out.println("2. Subtract the first number from the second");
        System.out.println("3. Multiply the first number by the second");
        System.out.println("4. Divide the first number by the second");
        System.out.println("5. Display input values and all results");
        System.out.println("6. Type new numbers");
        System.out.println("7. Leave");
        System.out.print("Select an option: ");
    }

    // Method for adding numbers
    public static double add(double number1, double number2) { return number1 + number2; }
    // Method for subtracting numbers
    public static double subs(double number1, double number2) { return number2 - number1; }
    // Method for multiplying numbers
    public static double multiply(double number1, double number2) { return number1 * number2; }
    // method for dividing numbers
    public static double divide(double number1, double number2) { return number1 / number2; }
    // Method for displaying inputs and all results
    public static void showAllResults(double number1, double number2, double addition, double subtraction, double multiplication, double division) {
        System.out.println("--- Summary ---");
        System.out.println("First Number: " + number1);
        System.out.println("Second Number: " + number2);
        System.out.println("Addition: " + addition);
        System.out.println("Subtraction (Number 2 - Number 1): " + subtraction);
        System.out.println("Multiplication: " + multiplication);
        System.out.println("Division (Number 1  / Number 2): " + division);
    }
}