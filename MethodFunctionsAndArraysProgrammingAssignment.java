import java.util.Scanner;

public class MethodFunctionsAndArraysProgrammingAssignment {

    // Constants for the maximum of employees
    static int quantityOfEmployees = 5;
    /* Constants for the calculations
    Use the following constants:
    • State Tax Rate: 7%
    • Federal Tax Rate: 15%
    • Hours Worked: 0–60 hours
    • Rate per Hour: $15.00–$35.0
     */
    static double stateTaxRate = 0.07;
    static double federalTaxRate = 0.15;
    static int[] hoursWorked = {0, 60};
    static double[] ratePerHour = {15.00, 35.00};


    public static void main(String[] args) {
        //The scanner that will be used so the user can type the information
        Scanner scanner = new Scanner(System.in);

        /* Arrays variables to save the employees information
        For each employee, store the following information:
        • First Name
        • Middle Initial
        • Last Name
        • ID Number
        • Hours Worked ( between 0 and 60 hours)
        • Rate per Hour (between $15.0 and $75.00)
        • Gross Pay
        • State Tax
        • Federal Tax
        • Net Pay
        Use appropriate arrays to store the employee information and calculated results*/

        String[] firstNames = new String[quantityOfEmployees];
        char[] middleInitials = new char[quantityOfEmployees];
        String[] lastNames = new String[quantityOfEmployees];
        int[] idNumbers = new int[quantityOfEmployees];
        double[] hours = new double[quantityOfEmployees];
        double[] rates = new double[quantityOfEmployees];
        double[] grossPayments = new double[quantityOfEmployees];
        double[] stateTaxes = new double[quantityOfEmployees];
        double[] federalTaxes = new double[quantityOfEmployees];
        double[] netPayments = new double[quantityOfEmployees];

        /*Calling the method for letting the user type the data
        For each of the 5 employees, your program must collect:
        • First Name
        • Middle Initial
        • Last Name
        • ID Number
        • Hours Worked
        • Rate per Hour
        Your program should use a method/function dedicated to input.
        If input validation is required, organize the validation into appropriate methods/functions rather
        than placing all validation logic into one large method/function.*/
        inputEmployeeData(scanner, lastNames, firstNames, middleInitials, idNumbers, rates, hours);

        //Calling the methods to do the calculations
        for (int i = 0; i < quantityOfEmployees; i++) {
            grossPayments[i] = calculateGrossPay(hours[i], rates[i]);
            stateTaxes[i] = calculateStateTax(grossPayments[i]);
            federalTaxes[i] = calculateFederalTax(grossPayments[i]);
            netPayments[i] = calculateNetPay(grossPayments[i], stateTaxes[i], federalTaxes[i]);
        }

        //Calling the method to show the results
        showResults(lastNames, firstNames, middleInitials, idNumbers, rates, hours, stateTaxes, federalTaxes, grossPayments, netPayments);

        scanner.close();
    }

    //Method for the user to write the information
    public static void inputEmployeeData(Scanner scanner, String[] lastName, String[] firstName, char[] middleInitial, int[] id, double[] rate, double[] hours) {
        System.out.println("=== Write the employees' information ===");
        for (int i = 0; i < quantityOfEmployees; i++) {
            System.out.println("\nEmployee #" + (i + 1));

            System.out.print("Last Name: ");
            lastName[i] = scanner.next();

            System.out.print("First Name: ");
            firstName[i] = scanner.next();

            System.out.print("Middle Initial: ");
            middleInitial[i] = scanner.next().charAt(0);

            System.out.print("ID Number: ");
            id[i] = scanner.nextInt();

            rate[i] = validateRate(scanner);
            hours[i] = validateHours(scanner);
        }
    }




    //Method to validate the rate per hour
    public static double validateRate(Scanner scanner) {
        double rate;
        do {
            System.out.print("Write the rate per hour have to be between $15.00 and $35.00): $");
            rate = scanner.nextDouble();
            if (rate < ratePerHour[0] || rate > ratePerHour[1]) { System.out.println("Error: Write the rate per hour between $15.00 and $35.00."); } }
        while (rate < ratePerHour[0] || rate > ratePerHour[1]);
        return rate;
    }

    //Method for the hours validation
    public static double validateHours(Scanner scanner) {
        double hours;
        do {
            System.out.print("Write the hours worked have to be between 0 and 60: ");
            hours = scanner.nextDouble();
            if (hours < hoursWorked[0] || hours > hoursWorked[1]) { System.out.println("Error: Write the hours worked between 0 and 60."); } }
        while (hours < hoursWorked[0] || hours > hoursWorked[1]);
        return hours;
    }



    //Method to calculate the gross pay
    /*
    Calculate gross pay using the following rules:
    40 Hours or Less
    If the employee works 40 hours or less:
    Gross Pay = Hours Worked × Rate per Hour
    More Than 40 Hours
    If the employee works more than 40 hours, the employee receives overtime pay.
    Regular Pay = 40 × Rate per Hour
    Overtime Hours = Hours Worked − 40
    Overtime Pay = Overtime Hours × Rate per Hour × 1.5
    Gross Pay = Regular Pay + Overtime Pay
    Create a separate method/function for calculating gross pay.
     */
    public static double calculateGrossPay(double hours, double rate) {
        if (hours <= 40) { return hours * rate;}
        else {
            double regularPay = calculateRegularPay(rate);
            double overtimeHours = calculateOvertimeHours(hours);
            double overtimePay = calculateOvertimePay(overtimeHours, rate);
            return regularPay + overtimePay;
        } }

    //Method to calculate the regular pay
    public static double calculateRegularPay(double rate) { return 40 * rate; }

    //Method to calculate the overtime hours
    public static double calculateOvertimeHours(double hours) { return hours - 40; }

    //Method to calculate the overtime pay
    public static double calculateOvertimePay(double overtimeHours, double rate) { return overtimeHours * rate * 1.5; }


    /*
    Tax Calculations
    Calculate the following:
    State Tax = Gross Pay × 7%
    Federal Tax = Gross Pay × 15%
    Net Pay = Gross Pay − (State Tax + Federal Tax)
    Each major calculation should be handled by its own method/function.
    For example:
    • One method/function calculates gross pay.
    • One method/function calculates state tax.
    • One method/function calculates federal tax.
    • One method/function calculates net pay.
    Remember:
    ONE method/function = ONE task.
    Do not combine all payroll calculations into one method/function.
     */

    //Method for calculating the state tax which is a 7%
    public static double calculateStateTax(double gross) { return gross * stateTaxRate; }

    //Method for calculating the federal tax which is a 15%
    public static double calculateFederalTax(double gross) { return gross * federalTaxRate; }

    //Method for calculating the net payment.
    public static double calculateNetPay(double gross, double state, double fed) { return gross - (state + fed); }




    //Method for showing all results data
    public static void showResults(String[] lastNames, String[] firstNames, char[] middleInitials, int[] idNumbers, double[] rates, double[] hours, double[] stateTaxes, double[] federalTaxes, double[] grossPayments, double[] netPayments) {
        System.out.println("\n" + "=".repeat(112));
        System.out.println("                                        Payroll Report                                         ");
        System.out.println("=".repeat(112));
        System.out.printf("%-12s %-12s %-3s %-6s %-10s %-8s %-10s %-10s %-10s %-10s%n",
                "Last Name", "First Name", "MI", "ID#", "Rate/Hour", "Hours Worked", "State Tax", "Fed Tax", "Gross", "Net");
        System.out.println("-------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < quantityOfEmployees; i++) {
            System.out.printf("%-12s %-12s %-3c %-6d $%-9.2f %-8.1f $%-9.2f $%-9.2f $%-9.2f $%-9.2f%n",
                    lastNames[i], firstNames[i], middleInitials[i], idNumbers[i], rates[i], hours[i], stateTaxes[i], federalTaxes[i], grossPayments[i], netPayments[i]);
        }
        System.out.println("=".repeat(112));
    }
}