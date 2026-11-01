/**
 * BankBalanceApp.java
 * Launcher for the GUI Bank Balance application.
 * Starts the interface on the Event Dispatch Thread, which is the safe
 * thread for building and showing Swing components.
 *
 * Critical Thinking Assignment, Module 2
 * Chris Jayson Salvadora
 */
import javax.swing.SwingUtilities;

public class BankBalanceApp {

    public static void main(String[] args) {
        try {
            // Build and show the window on the Event Dispatch Thread
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    BankAccountGUI gui = new BankAccountGUI();
                    gui.setVisible(true);
                }
            });
        } catch (Exception e) {
            System.out.println("Failure: application launch error. " + e.getMessage());
        }
    }
}
