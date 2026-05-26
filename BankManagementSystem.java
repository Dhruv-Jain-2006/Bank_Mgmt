import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.time.LocalDateTime;

// ===== ACCOUNT CLASS =====
class Account {

    String name;
    int age;
    String pin;
    long accountNo;
    double balance;
    ArrayList<String> history;

    public Account(String name, int age, String pin,
                   long accountNo, double balance) {

        this.name = name;
        this.age = age;
        this.pin = pin;
        this.accountNo = accountNo;
        this.balance = balance;
        this.history = new ArrayList<>();

        history.add("₹" + balance +
                " deposited on " + LocalDateTime.now());
    }
}

// ===== MAIN CLASS =====
public class BankManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Stores all users using account number
        HashMap<Long, Account> accounts = new HashMap<>();

        Random rand = new Random();

        while (true) {

            System.out.println("\n===== BANK MANAGEMENT SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Choose option: ");
            int mainChoice = sc.nextInt();
            sc.nextLine();

            switch (mainChoice) {

                // ===== CREATE ACCOUNT =====
                case 1:

                    System.out.println(
                            "\n===== ACCOUNT CREATION =====");

                    System.out.print("Enter your name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter your age: ");
                    int age = sc.nextInt();

                    if (age < 18) {

                        System.out.println(
                                "Age must be 18 or above!");
                        break;
                    }

                    sc.nextLine();

                    // PIN CREATION
                    String pin;

                    while (true) {

                        System.out.print(
                                "Create a 4-digit PIN: ");

                        pin = sc.nextLine();

                        if (pin.length() == 4) {

                            boolean valid = true;

                            for (int i = 0; i < pin.length(); i++) {

                                if (!Character.isDigit(
                                        pin.charAt(i))) {

                                    valid = false;
                                    break;
                                }
                            }

                            if (valid) {
                                break;
                            } else {
                                System.out.println(
                                        "PIN must contain only digits!");
                            }

                        } else {

                            System.out.println(
                                    "PIN must be exactly 4 digits!");
                        }
                    }

                    // ACCOUNT NUMBER GENERATION
                    long accountNo = 1000000000L +
                            (long)(rand.nextDouble()
                                    * 9000000000L);

                    // Ensure unique account number
                    while (accounts.containsKey(accountNo)) {

                        accountNo = 1000000000L +
                                (long)(rand.nextDouble()
                                        * 9000000000L);
                    }

                    System.out.print(
                            "Enter initial deposit: ₹");

                    double balance = sc.nextDouble();

                    if (balance <= 0) {

                        System.out.println(
                                "Deposit must be positive!");
                        break;
                    }

                    // CREATE ACCOUNT OBJECT
                    Account user = new Account(
                            name,
                            age,
                            pin,
                            accountNo,
                            balance
                    );

                    // STORE USER
                    accounts.put(accountNo, user);

                    System.out.println(
                            "\nAccount Created Successfully!");

                    System.out.println(
                            "Your Account Number: "
                                    + accountNo);

                    break;

                // ===== LOGIN =====
                case 2:

                    System.out.println(
                            "\n===== LOGIN =====");

                    System.out.print(
                            "Enter Account Number: ");

                    long enteredAcc = sc.nextLong();
                    sc.nextLine();

                    System.out.print("Enter PIN: ");
                    String enteredPin = sc.nextLine();

                    // CHECK ACCOUNT EXISTS
                    if (!accounts.containsKey(enteredAcc)) {

                        System.out.println(
                                "Account not found!");
                        break;
                    }

                    Account currentUser =
                            accounts.get(enteredAcc);

                    // VERIFY PIN
                    if (!currentUser.pin.equals(enteredPin)) {

                        System.out.println(
                                "Incorrect PIN!");
                        break;
                    }

                    System.out.println(
                            "\nLogin Successful!");

                    // ===== USER MENU =====
                    while (true) {

                        System.out.println(
                                "\n===== BANK MENU =====");

                        System.out.println("1. Deposit");
                        System.out.println("2. Withdraw");
                        System.out.println("3. Balance");
                        System.out.println("4. Interest Calculator");
                        System.out.println("5. Apply Loan");
                        System.out.println("6. Transaction History");
                        System.out.println("7. Logout");

                        System.out.print(
                                "Choose an option: ");

                        int choice = sc.nextInt();

                        switch (choice) {

                            // ===== DEPOSIT =====
                            case 1:

                                System.out.print(
                                        "Enter amount to deposit: ₹");

                                double deposit = sc.nextDouble();

                                if (deposit <= 0) {

                                    System.out.println(
                                            "Amount must be positive!");

                                } else {

                                    currentUser.balance += deposit;

                                    currentUser.history.add(
                                            "₹" + deposit +
                                                    " deposited on "
                                                    + LocalDateTime.now());

                                    System.out.println(
                                            "Deposit Successful!");

                                    System.out.println(
                                            "Updated Balance: ₹"
                                                    + currentUser.balance);
                                }

                                break;

                            // ===== WITHDRAW =====
                            case 2:

                                System.out.print(
                                        "Enter amount to withdraw: ₹");

                                double withdraw = sc.nextDouble();

                                if (withdraw <= 0) {

                                    System.out.println(
                                            "Amount must be positive!");

                                } else if (withdraw >
                                        currentUser.balance) {

                                    System.out.println(
                                            "Insufficient Balance!");

                                } else {

                                    currentUser.balance -= withdraw;

                                    currentUser.history.add(
                                            "₹" + withdraw +
                                                    " withdrawn on "
                                                    + LocalDateTime.now());

                                    System.out.println(
                                            "Withdrawal Successful!");

                                    System.out.println(
                                            "Remaining Balance: ₹"
                                                    + currentUser.balance);
                                }

                                break;

                            // ===== BALANCE =====
                            case 3:

                                System.out.println(
                                        "Current Balance: ₹"
                                                + currentUser.balance);

                                break;

                            // ===== INTEREST CALCULATOR =====
                            case 4:

                                System.out.print(
                                        "Enter interest rate (%): ");

                                double rate = sc.nextDouble();

                                System.out.print(
                                        "Enter time (years): ");

                                double time = sc.nextDouble();

                                if (rate < 0 || time < 0) {

                                    System.out.println(
                                            "Invalid values!");

                                } else {

                                    double interest =
                                            (currentUser.balance *
                                                    rate * time) / 100;

                                    System.out.println(
                                            "Interest Earned: ₹"
                                                    + interest);

                                    System.out.println(
                                            "Total Amount: ₹"
                                                    + (currentUser.balance
                                                    + interest));
                                }

                                break;

                            // ===== APPLY LOAN =====
                            case 5:

                                System.out.print(
                                        "Enter loan amount: ₹");

                                double loanAmount =
                                        sc.nextDouble();

                                System.out.print(
                                        "Enter annual interest rate (%): ");

                                double loanRate =
                                        sc.nextDouble();

                                System.out.print(
                                        "Enter duration (years): ");

                                int years =
                                        sc.nextInt();

                                if (loanAmount <= 0 ||
                                        loanRate < 0 ||
                                        years <= 0) {

                                    System.out.println(
                                            "Invalid loan details!");

                                } else {

                                    double simpleInterest =
                                            (loanAmount *
                                                    loanRate *
                                                    years) / 100;

                                    double totalPayable =
                                            loanAmount +
                                                    simpleInterest;

                                    double emi =
                                            totalPayable /
                                                    (years * 12);

                                    System.out.println(
                                            "\n===== LOAN DETAILS =====");

                                    System.out.println(
                                            "Loan Amount: ₹"
                                                    + loanAmount);

                                    System.out.println(
                                            "Interest Amount: ₹"
                                                    + simpleInterest);

                                    System.out.println(
                                            "Total Payable: ₹"
                                                    + totalPayable);

                                    System.out.println(
                                            "Monthly EMI: ₹"
                                                    + emi);

                                    currentUser.history.add(
                                            "Loan of ₹" +
                                                    loanAmount +
                                                    " applied on "
                                                    + LocalDateTime.now());
                                }

                                break;

                            // ===== TRANSACTION HISTORY =====
                            case 6:

                                System.out.println(
                                        "\n===== TRANSACTION HISTORY =====");

                                if (currentUser.history.isEmpty()) {

                                    System.out.println(
                                            "No transactions!");

                                } else {

                                    for (String transaction :
                                            currentUser.history) {

                                        System.out.println(
                                                transaction);
                                    }
                                }

                                break;

                            // ===== LOGOUT =====
                            case 7:

                                System.out.println(
                                        "Logged out successfully!");
                                break;

                            default:

                                System.out.println(
                                        "Invalid Choice!");
                        }

                        if (choice == 7) {
                            break;
                        }
                    }

                    break;

                // ===== EXIT =====
                case 3:

                    System.out.println(
                            "Thank you for using our Bank System!");

                    sc.close();
                    return;

                default:

                    System.out.println("Invalid Choice!");
            }
        }
    }
}
