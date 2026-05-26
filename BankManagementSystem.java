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

    // ===== CONSTRUCTOR =====
    public Account(String name, int age, String pin,
                   long accountNo, double balance) {

        this.name = name;
        this.age = age;
        this.pin = pin;
        this.accountNo = accountNo;
        this.balance = balance;

        history = new ArrayList<>();

        history.add("₹" + balance +
                " deposited on " + LocalDateTime.now());
    }
}

// ===== MAIN CLASS =====
public class BankManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Stores all accounts
        HashMap<Long, Account> accounts = new HashMap<>();

        Random rand = new Random();

        // ===== MAIN LOOP =====
        while (true) {

            System.out.println("\n===== BANK MANAGEMENT SYSTEM =====");

            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int mainChoice;

            // ===== EXCEPTION HANDLING =====
            while (true) {

                try {

                    System.out.print("Choose option: ");
                    mainChoice = Integer.parseInt(sc.nextLine());
                    break;

                } catch (Exception e) {

                    System.out.println(
                            "Invalid input! Enter numbers only.");
                }
            }

            switch (mainChoice) {

                // ===== CREATE ACCOUNT =====
                case 1:

                    System.out.println(
                            "\n===== ACCOUNT CREATION =====");

                    System.out.print("Enter your name: ");
                    String name = sc.nextLine();

                    int age;

                    while (true) {

                        try {

                            System.out.print("Enter your age: ");
                            age = Integer.parseInt(sc.nextLine());

                            if (age < 18) {

                                System.out.println(
                                        "Age must be 18 or above!");
                                continue;
                            }

                            break;

                        } catch (Exception e) {

                            System.out.println(
                                    "Invalid age!");
                        }
                    }

                    // ===== PIN CREATION =====
                    String pin;

                    while (true) {

                        System.out.print(
                                "Create a 4-digit PIN: ");

                        pin = sc.nextLine();

                        if (pin.length() == 4) {

                            boolean valid = true;

                            for (int i = 0;
                                 i < pin.length(); i++) {

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
                                        "PIN must contain digits only!");
                            }

                        } else {

                            System.out.println(
                                    "PIN must be exactly 4 digits!");
                        }
                    }

                    // ===== ACCOUNT NUMBER GENERATION =====
                    long accountNo = 1000000000L +
                            (long)(rand.nextDouble()
                                    * 9000000000L);

                    // Ensure uniqueness
                    while (accounts.containsKey(accountNo)) {

                        accountNo = 1000000000L +
                                (long)(rand.nextDouble()
                                        * 9000000000L);
                    }

                    double balance;

                    while (true) {

                        try {

                            System.out.print(
                                    "Enter initial deposit: ₹");

                            balance = Double.parseDouble(
                                    sc.nextLine());

                            if (balance <= 0) {

                                System.out.println(
                                        "Deposit must be positive!");
                                continue;
                            }

                            break;

                        } catch (Exception e) {

                            System.out.println(
                                    "Invalid amount!");
                        }
                    }

                    // ===== CREATE ACCOUNT OBJECT =====
                    Account user = new Account(
                            name,
                            age,
                            pin,
                            accountNo,
                            balance
                    );

                    // ===== STORE ACCOUNT =====
                    accounts.put(accountNo, user);

                    System.out.println(
                            "\nAccount Created Successfully!");

                    System.out.println(
                            "Your Account Number: "
                                    + accountNo);

                    break;

                // ===== LOGIN =====
                case 2:

                    System.out.println("\n===== LOGIN =====");

                    long enteredAcc;

                    try {

                        System.out.print(
                                "Enter Account Number: ");

                        enteredAcc = Long.parseLong(
                                sc.nextLine());

                    } catch (Exception e) {

                        System.out.println(
                                "Invalid Account Number!");
                        break;
                    }

                    System.out.print("Enter PIN: ");
                    String enteredPin = sc.nextLine();

                    // ===== CHECK ACCOUNT EXISTS =====
                    if (!accounts.containsKey(enteredAcc)) {

                        System.out.println(
                                "Account not found!");
                        break;
                    }

                    Account currentUser =
                            accounts.get(enteredAcc);

                    // ===== VERIFY PIN =====
                    if (!currentUser.pin.equals(
                            enteredPin)) {

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

                        int choice;

                        // ===== SAFE MENU INPUT =====
                        try {

                            System.out.print(
                                    "Choose an option: ");

                            choice = Integer.parseInt(
                                    sc.nextLine());

                        } catch (Exception e) {

                            System.out.println(
                                    "Invalid input! Enter numbers only.");
                            continue;
                        }

                        switch (choice) {

                            // ===== DEPOSIT =====
                            case 1:

                                double deposit;

                                try {

                                    System.out.print(
                                            "Enter amount to deposit: ₹");

                                    deposit = Double.parseDouble(
                                            sc.nextLine());

                                } catch (Exception e) {

                                    System.out.println(
                                            "Invalid amount!");
                                    continue;
                                }

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

                                double withdraw;

                                try {

                                    System.out.print(
                                            "Enter amount to withdraw: ₹");

                                    withdraw = Double.parseDouble(
                                            sc.nextLine());

                                } catch (Exception e) {

                                    System.out.println(
                                            "Invalid amount!");
                                    continue;
                                }

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

                                try {

                                    System.out.print(
                                            "Enter interest rate (%): ");

                                    double rate =
                                            Double.parseDouble(
                                                    sc.nextLine());

                                    System.out.print(
                                            "Enter time (years): ");

                                    double time =
                                            Double.parseDouble(
                                                    sc.nextLine());

                                    if (rate < 0 || time < 0) {

                                        System.out.println(
                                                "Invalid values!");

                                    } else {

                                        double interest =
                                                (currentUser.balance
                                                        * rate * time) / 100;

                                        System.out.println(
                                                "Interest Earned: ₹"
                                                        + interest);

                                        System.out.println(
                                                "Total Amount: ₹"
                                                        + (currentUser.balance
                                                        + interest));
                                    }

                                } catch (Exception e) {

                                    System.out.println(
                                            "Invalid input!");
                                }

                                break;

                            // ===== APPLY LOAN =====
                            case 5:

                                try {

                                    System.out.print(
                                            "Enter loan amount: ₹");

                                    double loanAmount =
                                            Double.parseDouble(
                                                    sc.nextLine());

                                    System.out.print(
                                            "Enter annual interest rate (%): ");

                                    double loanRate =
                                            Double.parseDouble(
                                                    sc.nextLine());

                                    System.out.print(
                                            "Enter duration (years): ");

                                    int years =
                                            Integer.parseInt(
                                                    sc.nextLine());

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

                                } catch (Exception e) {

                                    System.out.println(
                                            "Invalid input!");
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

                        // Exit user menu
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

                    System.out.println(
                            "Invalid Choice!");
            }
        }
    }
}
