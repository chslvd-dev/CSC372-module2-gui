/**
 * BankAccountGUI.java
 * Graphical interface for a simple bank balance application.
 * Builds a window with a JPanel, input field, and JButtons for deposit,
 * withdrawal, and showing the balance. Each button uses an ActionListener.
 * Reuses the BankAccount class from Module 1 for the account logic.
 *
 * Critical Thinking Assignment, Module 2
 * Chris Jayson Salvadora
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BankAccountGUI extends JFrame {

    // The account model that holds the balance
    private BankAccount account;

    // Interface components
    private JTextField amountField;
    private JLabel balanceLabel;
    private JLabel messageLabel;

    // Builds the window, panel, and buttons
    public BankAccountGUI() {
        try {
            // Create the account and set a starting balance from the user
            account = new BankAccount("Account", "Holder", 1001);
            double opening = readOpeningBalance();
            account.deposit(opening);

            setTitle("Bank Balance Application");
            setSize(360, 240);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            // Main panel uses a vertical layout to stack the rows
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            // Row for the amount label and input field
            JPanel inputRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
            inputRow.add(new JLabel("Amount:"));
            amountField = new JTextField(10);
            inputRow.add(amountField);

            // Row for the three action buttons
            JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton depositButton = new JButton("Deposit");
            JButton withdrawButton = new JButton("Withdraw");
            JButton showButton = new JButton("Show Balance");
            buttonRow.add(depositButton);
            buttonRow.add(withdrawButton);
            buttonRow.add(showButton);

            // Labels that report the balance and status messages
            balanceLabel = new JLabel("Balance: $" + String.format("%.2f", account.getBalance()));
            messageLabel = new JLabel(" ");

            JPanel infoRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
            infoRow.add(balanceLabel);
            JPanel messageRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
            messageRow.add(messageLabel);

            panel.add(inputRow);
            panel.add(buttonRow);
            panel.add(infoRow);
            panel.add(messageRow);

            // ActionListener for the Deposit button
            depositButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    handleDeposit();
                }
            });

            // ActionListener for the Withdraw button
            withdrawButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    handleWithdraw();
                }
            });

            // ActionListener for the Show Balance button
            showButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showBalance();
                }
            });

            add(panel);
        } catch (Exception e) {
            System.out.println("Failure: GUI constructor error. " + e.getMessage());
        }
    }

    // Prompts the user for a starting balance when the app opens
    private double readOpeningBalance() {
        try {
            String input = JOptionPane.showInputDialog(this,
                    "Enter the opening balance:", "0.00");
            if (input == null) {
                return 0.0;
            }
            return Double.parseDouble(input.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid number. Opening balance set to 0.");
            return 0.0;
        }
    }

    // Reads the amount field and returns a valid double, or -1 on error
    private double readAmount() {
        try {
            double amount = Double.parseDouble(amountField.getText().trim());
            if (amount < 0) {
                messageLabel.setText("Enter a positive amount.");
                return -1;
            }
            return amount;
        } catch (NumberFormatException e) {
            messageLabel.setText("Enter a valid number.");
            return -1;
        }
    }

    // Adds the entered amount to the balance
    private void handleDeposit() {
        try {
            double amount = readAmount();
            if (amount < 0) {
                return;
            }
            account.deposit(amount);
            messageLabel.setText("Deposited $" + String.format("%.2f", amount));
            updateBalance();
        } catch (Exception e) {
            messageLabel.setText("Deposit error.");
        }
    }

    // Subtracts the entered amount if funds allow
    private void handleWithdraw() {
        try {
            double amount = readAmount();
            if (amount < 0) {
                return;
            }
            if (amount > account.getBalance()) {
                messageLabel.setText("Insufficient funds.");
                return;
            }
            account.withdrawal(amount);
            messageLabel.setText("Withdrew $" + String.format("%.2f", amount));
            updateBalance();
        } catch (Exception e) {
            messageLabel.setText("Withdrawal error.");
        }
    }

    // Shows the current balance in a dialog, which satisfies the display requirement
    private void showBalance() {
        try {
            JOptionPane.showMessageDialog(this,
                    "Current balance: $" + String.format("%.2f", account.getBalance()),
                    "Balance", JOptionPane.INFORMATION_MESSAGE);
            updateBalance();
        } catch (Exception e) {
            messageLabel.setText("Display error.");
        }
    }

    // Refreshes the balance label after any change
    private void updateBalance() {
        try {
            balanceLabel.setText("Balance: $" + String.format("%.2f", account.getBalance()));
            amountField.setText("");
        } catch (Exception e) {
            messageLabel.setText("Update error.");
        }
    }
}
