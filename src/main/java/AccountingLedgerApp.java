import java.util.ArrayList;
import java.util.Scanner;

public class AccountingLedgerApp {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        homePage(transactions);
        addDeposit(transactions);
        ledger(transactions);


        {
            System.out.print("""
                    
                    What do you want to do?
                         1. Home
                         2. Ledger
                         0. Exit
                     Make you selection:
                    """);

            int mainScreen = input.nextInt();

            switch (mainScreen) {
                case 1:
                    homePage(transactions);
                    break;

                case 2:
                    ledger(transactions);
                    break;
                case 3:
                    System.exit(0);

            }
        }
         String choice = input.nextLine().toUpperCase();
    }

    public static void homePage(ArrayList<Transaction> transactions) {
        System.out.print("""
                Welcome to the HomeScreen
                
                Choose from the list:
                
                ▪ Add Deposit - (D)
                ▪ P) Make Payment
                ▪ L) Ledger
                  M) Main Menu
                ▪ X) Exit
                """);

        String choice = input.nextLine().toUpperCase();

        switch (choice){
            case "D":
                addDeposit(transactions);
                break;
            case "P":
                break;
            case "L":
                ledger(transactions);




        }


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

    }
    public static void loadTransaction(ArrayList<Transaction> transactions){

    }
    public static void addDeposit(ArrayList<Transaction> transactions){
        System.out.println("Enter date: ");
        String date = input.nextLine();
        System.out.println("Enter Description: ");
        String describe = input.nextLine();
        double amount = input.nextDouble();


    }
    public static void saveTransaction(ArrayList<Transaction> transactions){

    }
}