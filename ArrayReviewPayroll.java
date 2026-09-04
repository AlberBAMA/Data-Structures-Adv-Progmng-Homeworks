    import java.util.Scanner;

    public class ArrayReviewPayroll {

        //Settings variables Alber Baez
        static double stateTaxRate = 0.07;
        static double federalTaxRate = 0.15;
        static double regularHours = 40.0;
        static double extraRateHours = 1.5;
        static double minHours = 0.0;
        static double maxHours = 60.0;
        static double minRatePerHours = 15.0;
        static double maxRatePerHours = 35.0;

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            //Variable to control the for loop that is going to be writing the employees' information.
            int employeesQuantity = 5;

            //Arrays to save the employees' information.
                String[] firstNames = new String[employeesQuantity];
                char[] middleInitials = new char[employeesQuantity];
                String[] lastNames = new String[employeesQuantity];
                String[] idNumbers = new String[employeesQuantity];
                double[] hours = new double[employeesQuantity];
                double[] rates = new double[employeesQuantity];
                double[] grossPayments = new double[employeesQuantity];
                double[] stateTaxes = new double[employeesQuantity];
                double[] federalTaxes = new double[employeesQuantity];
                double[] netPayments = new double[employeesQuantity];

                //Loop to collect the data
                for (int i = 0; i < employeesQuantity; i++) {
                    System.out.println("\n--- Employee Data #" + (i + 1) + " ---");

                    // Personal Data
                    System.out.print("First name: ");
                    firstNames[i] = scanner.next();
                    System.out.print("Middle initial: ");
                    middleInitials[i] = scanner.next().charAt(0);
                    System.out.print("Last name: ");
                    lastNames[i] = scanner.next();
                    System.out.print("ID number: ");
                    idNumbers[i] = scanner.next();

                    //Validate Rate per hour and hours
                    rates[i] = checkValue(scanner, "Rate per hour ($)", minRatePerHours, maxRatePerHours);
                    hours[i] = checkValue(scanner, "Hours worked", minHours, maxHours);

                    //Call methods to do the calculations
                    grossPayments[i] = calculateGrossPayment(hours[i], rates[i]);
                    stateTaxes[i] = calculateStateTax(grossPayments[i]);
                    federalTaxes[i] = calculateFederalTax(grossPayments[i]);
                    netPayments[i] = calculateNetPayment(grossPayments[i], stateTaxes[i], federalTaxes[i]);
                }

                //Print all results data
                System.out.println("\n" + "=".repeat(112));
                System.out.println("                               Payroll Report");
                System.out.println("=".repeat(112));
                System.out.printf("%-12s %-12s %-4s %-10s %-10s %-8s %-12s %-12s %-12s %-12s%n",
                        "Last Name", "First Name", "MI", "ID#", "Rate/Hour", "Hours Worked", "State Tax", "Fed Tax", "Gross", "Net");
                System.out.println("-".repeat(112));

                for (int i = 0; i < employeesQuantity; i++) {
                    System.out.printf("%-12s %-12s %-4c %-10s $%-9.2f %-8.1f $%-11.2f $%-11.2f $%-11.2f $%-11.2f%n",
                            lastNames[i], firstNames[i], middleInitials[i], idNumbers[i], rates[i], hours[i], stateTaxes[i], federalTaxes[i], grossPayments[i], netPayments[i]);
                }
                System.out.println("=".repeat(112));

                scanner.close();
            }

            //Method for validate rate per hour and hours worked
            public static double checkValue(Scanner scanner, String field, double min, double max) {
                double value;
                do {
                    System.out.printf("%s (between %.2f and %.2f): ", field, min, max);
                    while (!scanner.hasNextDouble()) {
                        System.out.print("Please type a valid number: ");
                        scanner.next();
                    }
                    value = scanner.nextDouble();
                    if (value < min || value > max) {
                        System.out.printf("Error: %s have to be between %.2f y %.2f.%n", field, min, max);
                    }
                } while (value < min || value > max);
                return value;
            }


            //Method for calculating the gross payment and extra hours
            public static double calculateGrossPayment(double hours, double rate) {
                if (hours <= regularHours) {return hours * rate;}
                else {
                    double extraHours = hours - regularHours;
                    return (regularHours * rate) + (extraHours * rate * extraRateHours);
                }
            }
            //Method for calculating the state tax.
            public static double calculateStateTax(double gross) { return gross * stateTaxRate;}
            //Method for calculating the federal tax.
            public static double calculateFederalTax(double gross) { return gross * federalTaxRate;}
            //Method for calculating the net payment.
            public static double calculateNetPayment(double gross, double statal, double federal) { return gross - (statal + federal);}

    }
