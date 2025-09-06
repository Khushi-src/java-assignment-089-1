package bankaccount;

public class Account {
    
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private String email;
    private String phoneNumber;

    
    public Account(int accountNumber, String accountHolderName,
                   double initialDeposit, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

  
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
        } else {
            balance += amount;
            System.out.printf("Successfully deposited %.2f. New balance: %.2f%n", amount, balance);
        }
    }

    
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            System.out.printf("Successfully withdrew %.2f. New balance: %.2f%n", amount, balance);
        }
    }

    
    public void displayAccountDetails() {
        System.out.println("Account Number   : " + accountNumber);
        System.out.println("Account Holder   : " + accountHolderName);
        System.out.printf("Balance          : %.2f%n", balance);
        System.out.println("Email            : " + email);
        System.out.println("Phone Number     : " + phoneNumber);
    }

   
    public void updateContactDetails(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        System.out.println("Contact details updated successfully.");
    }

  
    public int getAccountNumber() {
        return accountNumber;
    }
}

