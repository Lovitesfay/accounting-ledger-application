import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountingLedgerApp {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        ArrayList<Transaction> transactions = new ArrayList<>();

        while (true) {
            {
                System.out.print("""
                        
                        What do you want to do?
                             1. Home
                             2. Ledger
                             0. Exit
                         Make you selection:
                        """);

                String mainScreen = input.nextLine();

                switch (mainScreen) {
                    case "1":
                        homePage(transactions);
                        break;

                    case "2":
                        ledger(transactions);
                        break;
                    case "0":
                        System.exit(0);

                }
            }
        }
    }

    public static void homePage(ArrayList<Transaction> transactions) throws IOException {
        while (true) {
            System.out.print("""
                    
                    ▪ Add Deposit - (D)
                    ▪ P) Make Payment
                    ▪ L) Ledger
                    ▪ X) Exit
                    
                    What would you like to do today?
                    """);

            String choice = input.nextLine().toUpperCase();

            switch (choice) {
                case "D":
                    addDeposit(transactions);
                    break;
                case "P":
                    makePayment(transactions);
                    break;
                case "L":
                    ledger(transactions);
                    break;
                case "X":
                    return;


            }

        }
    }

    public static void addDeposit(ArrayList<Transaction> transactions) throws IOException {
        System.out.println("Please fill in the blank");

        System.out.print("Enter date: ");
        String date = input.nextLine();
        System.out.println("Enter Time: ");
        String time = input.nextLine();
        System.out.print("Enter Description: ");
        String describe = input.nextLine();
        System.out.println("Vendor Name: ");
        String vendor = input.nextLine();
        System.out.print("Enter amount: ");
        double amount = input.nextDouble();
        input.nextLine();
        System.out.println(" ");
        Transaction transact = new Transaction(date, time, amount);

        transactions.add(transact);
        saveTransaction(transact);

        System.out.println("We've received your Deposit!");
        return;

    }

    public static void makePayment(ArrayList<Transaction> transactions) throws IOException {

        System.out.println("Please fill in the blank");
        System.out.println(" ");
        System.out.println("[Make a Payment]");
        System.out.println(" ");

        System.out.print("Enter date: ");
        String date = input.nextLine();
        System.out.println("Enter Time: ");
        String time = input.nextLine();
        System.out.print("Enter Description: ");
        String describe = input.nextLine();
        System.out.println("Vendor Name: ");
        String vendor = input.nextLine();
        System.out.print("Enter amount: ");
        double amount = input.nextDouble();
        input.nextLine();
        amount = -Math.abs(amount);
        System.out.println(" ");
        Transaction transact = new Transaction(date, describe, amount);

        transactions.add(transact);
        saveTransaction(transact);

        System.out.println("We've received your Payment !");
    }

    public static void ledger(ArrayList<Transaction> transactions) {
        System.out.println("""
                Ledger - All entries should show the newest entries first
                o A) All
                o D) Deposits
                o P) Payments
                o R) Reports
                ▪ 1) Month To Date
                ▪ 2) Previous Month
                ▪ 3) Year To Date
                ▪ 4) Previous Year
                ▪ 5) Search by Vendor
                ▪ 0) Back - go back to the Ledger page
                o H) Home - go back to the home page
                
                """);

        String ledger = input.nextLine();
    }

    public static void loadTransaction(ArrayList<Transaction> transactions) {

    }

    public static void saveTransaction(Transaction transaction) throws IOException {
       try (BufferedWriter writeBuffer = new BufferedWriter(new FileWriter
               ("src/main/resources/transactions.csv"))){
           writeBuffer.write("");
           writeBuffer.newLine();
           writeBuffer.write("                           Transactions                                ");
           writeBuffer.newLine();
           writeBuffer.write("______________________________________________________________________");
           writeBuffer.newLine();
           writeBuffer.write(" ");
           writeBuffer.newLine();
           writeBuffer.write("date|description|amount");
           writeBuffer.newLine();
           writeBuffer.write(transaction.getDate()+  "|" +
                   transaction.getDescription() + "|" +
                   transaction.getAmount());


           writeBuffer.close();
       }

}
    }
