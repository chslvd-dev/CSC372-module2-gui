/**
 * BankAccount.java
 * Superclass that represents a basic bank account.
 * Stores the account holder name, an account ID, and a balance.
 * The CheckingAccount subclass inherits from this class.
 *
 * Critical Thinking Assignment
 * Chris Jayson Salvadora
 */
public class BankAccount {

    // Private attributes required by the assignment
    private String firstName;
    private String lastName;
    private int accountID;
    private double balance;

    // Constructor initializes balance to zero
    public BankAccount() {
        try {
            this.firstName = "";
            this.lastName = "";
            this.accountID = 0;
            this.balance = 0.0;
        } catch (Exception e) {
            System.out.println("Failure: constructor error. " + e.getMessage());
        }
    }

    // Parameterized constructor builds an account and starts balance at zero
    public BankAccount(String firstName, String lastName, int accountID) {
        try {
            this.firstName = firstName;
            this.lastName = lastName;
            this.accountID = accountID;
            this.balance = 0.0;
        } catch (Exception e) {
            System.out.println("Failure: constructor error. " + e.getMessage());
        }
    }

    // Adds the parameter value to the existing balance
    public void deposit(double amount) {
        try {
            balance = balance + amount;
        } catch (Exception e) {
            System.out.println("Failure: deposit error. " + e.getMessage());
        }
    }

    // Subtracts the parameter value from the existing balance
    public void withdrawal(double amount) {
        try {
            balance = balance - amount;
        } catch (Exception e) {
            System.out.println("Failure: withdrawal error. " + e.getMessage());
        }
    }

    // Setters for firstName, lastName, and accountID
    public void setFirstName(String firstName) {
        try { this.firstName = firstName; }
        catch (Exception e) { System.out.println("Failure: set firstName error. " + e.getMessage()); }
    }

    public void setLastName(String lastName) {
        try { this.lastName = lastName; }
        catch (Exception e) { System.out.println("Failure: set lastName error. " + e.getMessage()); }
    }

    public void setAccountID(int accountID) {
        try { this.accountID = accountID; }
        catch (Exception e) { System.out.println("Failure: set accountID error. " + e.getMessage()); }
    }

    // Getters for firstName, lastName, and accountID
    public String getFirstName() {
        try { return firstName; }
        catch (Exception e) { return ""; }
    }

    public String getLastName() {
        try { return lastName; }
        catch (Exception e) { return ""; }
    }

    public int getAccountID() {
        try { return accountID; }
        catch (Exception e) { return 0; }
    }

    // Getter to return the balance
    public double getBalance() {
        try { return balance; }
        catch (Exception e) { return 0.0; }
    }

    // Allows subclasses to set the balance directly for overdraft handling
    protected void setBalance(double balance) {
        try { this.balance = balance; }
        catch (Exception e) { System.out.println("Failure: set balance error. " + e.getMessage()); }
    }

    // Prints all account information
    public void accountSummary() {
        try {
            System.out.println("Account Summary");
            System.out.println("Name: " + firstName + " " + lastName);
            System.out.println("Account ID: " + accountID);
            System.out.printf("Balance: $%.2f%n", balance);
        } catch (Exception e) {
            System.out.println("Failure: accountSummary error. " + e.getMessage());
        }
    }
}
