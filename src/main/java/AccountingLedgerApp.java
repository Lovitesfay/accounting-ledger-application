import java.util.ArrayList;
import java.util.Scanner;

public class AccountingLedgerApp {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Transaction> transactions = new ArrayList<>();
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

        int ledger = input.nextInt();

    }
    public static void loadTransaction(ArrayList<Transaction> transactions){

    }
    public static void addDeposit(ArrayList<Transaction> transactions){

    }
    public static void saveTransaction(ArrayList<Transaction> transactions){

    }
}