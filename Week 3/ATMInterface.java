import java.util.Scanner;

// Bank Account Class
class BankAccount {
    private double balance;

    // Constructor
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(" Amount deposited successfully.");
        } else {
            System.out.println(" Invalid deposit amount.");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println(" Invalid withdrawal amount.");
        } else if (amount > balance) {
            System.out.println(" Insufficient balance.");
        } else {
            balance -= amount;
            System.out.println(" Withdrawal successful.");
        }
    }

    // Check balance
    public double getBalance() {
        return balance;
    }
}

// ATM Class
class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void showMenu() {
        System.out.println("\n===== ATM MENU =====");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }
}

// Main Class
public class ATMInterface {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Create account with initial balance
        BankAccount account = new BankAccount(1000);
        ATM atm = new ATM(account);

        int choice;

        do {
            atm.showMenu();
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println(" Current Balance: ₹" + account.getBalance());
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;

                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;

                case 4:
                    System.out.println(" Thank you for using ATM!");
                    break;

                default:
                    System.out.println(" Invalid option. Try again.");
            }

        } while (choice != 4);

        scanner.close();
    }
}

