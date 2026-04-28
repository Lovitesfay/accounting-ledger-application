import java.util.ArrayList;
import java.util.Scanner;

public class AccountingLedgerApp {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
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

    public static void homePage(ArrayList<Transaction> transactions) {
       while(true) {
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

    public static void addDeposit(ArrayList<Transaction> transactions){
        System.out.println("Please fill in the blank");

        System.out.print("Enter date: ");
        String date = input.nextLine();
        System.out.print("Enter Description: ");
        String describe = input.nextLine();
        System.out.print("Enter amount: ");
        double amount = input.nextDouble();
        input.nextLine();
        System.out.println(" ");
        Transaction transact = new Transaction(date, describe, amount);

        transactions.add(transact);
        saveTransaction(transact);

        System.out.println("We've received your Deposit!");
        return;

    }

    public static void makePayment(ArrayList<Transaction> transactions){

        System.out.println("Please fill in the blank");
        System.out.println("[Make a Deposit]");

        System.out.print("Enter date: ");
        String date = input.nextLine();
        System.out.print("Enter Description: ");
        String describe = input.nextLine();
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

    public static void ledger(ArrayList<Transaction>transactions) {
        System.out.println("""
                Ledger - All entries should show the newest entries first
                o A) All - Display all entries
                o D) Deposits - Display only the entries that are deposits into the account
                o P) Payments - Display only the negative entries (or payments)
                o R) Reports - A new screen that allows the user to run pre-defined reports or
                to run a custom search
                ▪ 1) Month To Date
                ▪ 2) Previous Month
                ▪ 3) Year To Date
                ▪ 4) Previous Year
                ▪ 5) Search by Vendor - prompt the user for the vendor name and
                display all entries for that vendor
                ▪ 0) Back - go back to the Ledger page
                o H) Home - go back to the home page
                
                """);

        int ledger = input.nextInt();
        input.nextLine();
    }

    public static void loadTransaction(ArrayList<Transaction> transactions){

    }

    public static void saveTransaction(Transaction transaction){

    }
}