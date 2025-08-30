package bankaccount;

import java.util.Scanner;

    /**
     * UserInterface.java
     */
    public class UserInterface {
        private Account[] accounts;          // Single-dimensional array of accounts
        private double[][] transactions;     // Multi-dimensional array: [index][0]=lastDeposit, [index][1]=lastWithdrawal
        private Scanner scanner;
        private int accountCount;            // Number of accounts created
        private int nextAccountNumber;       // Auto-incrementing account number

        // Constructor
        public UserInterface() {
            accounts = new Account[100];
            transactions = new double[100][2];
            scanner = new Scanner(System.in);
            accountCount = 0;
            nextAccountNumber = 1001;        // Starting account number
        }

        // Main menu loop
        public void mainMenu() {
            int choice;
            do {
                System.out.println("\nWelcome to the Banking Application!");
                System.out.println("1. Create a new account");
                System.out.println("2. Deposit money");
                System.out.println("3. Withdraw money");
                System.out.println("4. View account details");
                System.out.println("5. Update contact details");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1: createAccount();       break;
                    case 2: performDeposit();      break;
                    case 3: performWithdrawal();   break;
                    case 4: showAccountDetails();  break;
                    case 5: updateContact();       break;
                    case 6: System.out.println("Thank you for using the application."); break;
                    default: System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 6);

            scanner.close();
        }

        // 1. Create a new account
        private void createAccount() {
            System.out.print("Enter account holder name: ");
            String name = scanner.nextLine();

            System.out.print("Enter initial deposit amount: ");
            double initialDeposit = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter email address: ");
            String email = scanner.nextLine();

            System.out.print("Enter phone number: ");
            String phone = scanner.nextLine();

            Account newAccount = new Account(nextAccountNumber, name, initialDeposit, email, phone);
            accounts[accountCount] = newAccount;
            System.out.println("Account created successfully with Account Number: " + nextAccountNumber);

            // Initialize transaction log for this account
            transactions[accountCount][0] = 0;  // last deposit
            transactions[accountCount][1] = 0;  // last withdrawal

            accountCount++;
            nextAccountNumber++;
        }

        // 2. Deposit operation
        private void performDeposit() {
            System.out.print("Enter account number: ");
            int accNum = scanner.nextInt();
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            int idx = findAccountIndex(accNum);

            if (idx != -1) {
                accounts[idx].deposit(amount);
                transactions[idx][0] = amount;
            } else {
                System.out.println("Account not found.");
            }
        }

        // 3. Withdrawal operation
        private void performWithdrawal() {
            System.out.print("Enter account number: ");
            int accNum = scanner.nextInt();
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            int idx = findAccountIndex(accNum);

            if (idx != -1) {
                accounts[idx].withdraw(amount);
                transactions[idx][1] = amount;
            } else {
                System.out.println("Account not found.");
            }
        }

        // 4. Show account details
        private void showAccountDetails() {
            System.out.print("Enter account number: ");
            int accNum = scanner.nextInt();
            int idx = findAccountIndex(accNum);

            if (idx != -1) {
                accounts[idx].displayAccountDetails();
                System.out.printf("Last Deposit     : %.2f%n", transactions[idx][0]);
                System.out.printf("Last Withdrawal  : %.2f%n", transactions[idx][1]);
            } else {
                System.out.println("Account not found.");
            }
        }

        // 5. Update contact details
        private void updateContact() {
            System.out.print("Enter account number: ");
            int accNum = scanner.nextInt();
            scanner.nextLine();

            int idx = findAccountIndex(accNum);
            if (idx != -1) {
                System.out.print("Enter new email address: ");
                String email = scanner.nextLine();
                System.out.print("Enter new phone number: ");
                String phone = scanner.nextLine();
                accounts[idx].updateContactDetails(email, phone);
            } else {
                System.out.println("Account not found.");
            }
        }

        // Helper: find account index by account number
        private int findAccountIndex(int accNum) {
            for (int i = 0; i < accountCount; i++) {
                if (accounts[i].getAccountNumber() == accNum) {
                    return i;
                }
            }
            return -1;
        }

        // Entry point
        public static void main(String[] args) {
            UserInterface ui = new UserInterface();
            ui.mainMenu();
        }
    }
