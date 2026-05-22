import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.time.LocalDateTime;

class BankManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<String> history = new ArrayList<>();

        // Account Creation
        System.out.println("===== BANK ACCOUNT CREATION =====");

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter your age: ");
        int age = sc.nextInt();

        // Age Validation
        if (age < 18) {
            System.out.println("Error: Cannot create account. Age must be 18 or above.");
            return;
        }

        sc.nextLine();

        // PIN Creation
        String pin;

        while (true) {

            System.out.print("Create a 4-digit PIN: ");
            pin = sc.nextLine();

            if (pin.length() == 4) {

                boolean valid = true;

                for (int i = 0; i < pin.length(); i++) {
                    if (!Character.isDigit(pin.charAt(i))) {
                        valid = false;
                        break;
                    }
                }

                if (valid) {
                    break;
                } else {
                    System.out.println("PIN must contain only digits!");
                }

            } else {
                System.out.println("PIN must be exactly 4 digits!");
            }
        }

        // Generate 10-digit Account Number
        Random rand = new Random();
        long accountNo = 1000000000L + (long)(rand.nextDouble() * 9000000000L);

        System.out.println("\nAccount created successfully!");
        System.out.println("Your Account Number: " + accountNo);

        // Initial Deposit
        double balance;

        while (true) {

            System.out.print("Enter minimum amount to deposit: ₹");
            balance = sc.nextDouble();

            if (balance <= 0) {
                System.out.println("Amount cannot be negative or zero!");
            } else {
                break;
            }
        }

        history.add("₹" + balance + " deposited on " + LocalDateTime.now());

        System.out.println("Amount deposited successfully!");
        System.out.println("Current Balance: ₹" + balance);

        // Login System
        System.out.println("\n===== LOGIN SYSTEM =====");

        while (true) {

            System.out.print("Enter Account Number: ");
            long enteredAcc = sc.nextLong();

            sc.nextLine();

            System.out.print("Enter PIN: ");
            String enteredPin = sc.nextLine();

            if (enteredAcc == accountNo && enteredPin.equals(pin)) {
                System.out.println("\nLogin Successful!");
                break;
            } else {
                System.out.println("Wrong Credentials!");
            }
        }

        // Banking Interface
        while (true) {

            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Balance");
            System.out.println("4. Interest Calculator");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    // Deposit
                    System.out.print("Enter amount to deposit: ₹");
                    double deposit = sc.nextDouble();

                    if (deposit <= 0) {
                        System.out.println("Deposit amount must be positive!");
                    } else {

                        balance += deposit;

                        history.add("₹" + deposit + " deposited on " + LocalDateTime.now());

                        System.out.println("Deposit Successful!");
                        System.out.println("Updated Balance: ₹" + balance);
                    }

                    break;

                case 2:

                    // Withdraw
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdraw = sc.nextDouble();

                    if (withdraw <= 0) {
                        System.out.println("Withdrawal amount must be positive!");
                    } else if (withdraw > balance) {
                        System.out.println("Insufficient Balance!");
                    } else {

                        balance -= withdraw;

                        history.add("₹" + withdraw + " withdrawn on " + LocalDateTime.now());

                        System.out.println("Withdrawal Successful!");
                        System.out.println("Remaining Balance: ₹" + balance);
                    }

                    break;

                case 3:

                    // Balance
                    System.out.println("Current Balance: ₹" + balance);
                    break;

                case 4:

                    // Interest Calculator
                    System.out.print("Enter interest rate (%): ");
                    double rate = sc.nextDouble();

                    System.out.print("Enter time (in years): ");
                    double time = sc.nextDouble();

                    if (rate < 0 || time < 0) {
                        System.out.println("Rate and time cannot be negative!");
                    } else {

                        double interest = (balance * rate * time) / 100;

                        System.out.println("Interest Earned: ₹" + interest);
                        System.out.println("Total Amount after Interest: ₹" + (balance + interest));
                    }

                    break;

                case 5:

                    // Transaction History
                    System.out.println("\n===== TRANSACTION HISTORY =====");

                    if (history.isEmpty()) {
                        System.out.println("No transactions found!");
                    } else {

                        for (String transaction : history) {
                            System.out.println(transaction);
                        }
                    }

                    break;

                case 6:

                    // Exit
                    System.out.println("Thank you for using our Bank System!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}