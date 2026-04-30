import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AccountingLedgerApp {

    public static Scanner input = new Scanner(System.in);

    static DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) throws IOException {
        ArrayList<Transaction> transactions = new ArrayList<>();
        readTransaction(transactions);
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
                        homeScreen(transactions);
                        break;
                    case "2":
                        ledgerScreen(transactions);
                        break;
                    case "0":
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;

                }
            }
        }
    }

    public static void homeScreen(ArrayList<Transaction> transactions)
            throws IOException {
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
                    ledgerScreen(transactions);
                    break;
                case "X":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;

            }

        }
    }

    public static void addDeposit(ArrayList<Transaction> transactions)
            throws IOException {
        System.out.println("Please fill in the blank");
        String date = LocalDate.now().toString();
        String time = LocalTime.now().toString().substring(0,8 );

        System.out.print("Enter Description: ");
        String describe = input.nextLine();
        System.out.println("Vendor Name: ");
        String vendor = input.nextLine();
        System.out.print("Enter amount: ");
        double amount = input.nextDouble();
        input.nextLine();
        amount = Math.abs(amount);
        System.out.println(" ");
        Transaction transact = new Transaction(date, time, describe, vendor, amount);

        transactions.add(transact);
        writeTransaction(transact);

        System.out.println("We've received your Deposit!");


    }

    public static void makePayment(ArrayList<Transaction> transactions)
            throws IOException {

        String date = LocalDate.now().toString();
        String time = LocalTime.now().toString().substring(0, 8);

        System.out.println("Please fill in the blank");
        System.out.println(" ");
        System.out.println("[Make a Payment]");
        System.out.println(" ");

        System.out.print("Enter Description: ");
        String describe = input.nextLine();

        System.out.print("Vendor Name: ");
        String vendor = input.nextLine();

        System.out.print("Enter amount: ");
        double amount = input.nextDouble();
        input.nextLine();
        amount = -Math.abs(amount);
        System.out.println(" ");
        Transaction transact = new Transaction(date, time, describe, vendor, amount);

        transactions.add(transact);
        writeTransaction(transact);

        System.out.println("We've received your Payment !");
    }

    public static void ledgerScreen(ArrayList<Transaction> transactions) throws IOException {
        while (true) {
            System.out.println("""
                    Ledger - All entries should show the newest entries first
                    o A) All
                    o D) Deposits
                    o P) Payments
                    o R) Reports
                    o H) Home
                    """);

            String choice = input.nextLine().toUpperCase();

            switch (choice) {
                case "A":
                    listAll(transactions);
                    break;
                case "D":
                    listDeposits(transactions);
                    break;
                case "P":
                    listPayments(transactions);
                    break;
                case "R":
                    listReports(transactions);
                    break;
                case "H":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");


            }


        }
    }
    public static void listAll(ArrayList<Transaction> transactions) {
        System.out.println("Here you can see everything");
        System.out.println("\n====== ALL TRANSACTIONS ======\n");

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);

            System.out.println(
                    transaction.getDate() + " | " +
                    transaction.getTime() + " | " +
                    transaction.getDescription() + " | " +
                    transaction.getVendor() + " | " +
                    transaction.getAmount()
            );
        }
    }

    public static void listDeposits(ArrayList<Transaction> transactions) {
        System.out.println("List of the Deposits made");
        System.out.println(" ");
        System.out.println("\n====== DEPOSITS ======\n");


        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);

            if (transaction.getAmount() > 0) {
                System.out.println(
                        transaction.getDate() + " | " +
                        transaction.getTime() + " | " +
                        transaction.getDescription() + " | " +
                        transaction.getVendor() + " | " +
                        transaction.getAmount()
                );
            }
        }
    }

    public static void listPayments(ArrayList<Transaction>transactions) {
        System.out.println("\n====== PAYMENTS ======\n");



        for (int j = transactions.size() - 1; j >= 0; j--) {
            Transaction transaction = transactions.get(j);

            if (transaction.getAmount() < 0) {
                System.out.println(
                        transaction.getDate() + " | " +
                        transaction.getTime() + " | " +
                        transaction.getDescription() + " | " +
                        transaction.getVendor() + " | " +
                        transaction.getAmount()
                );
            }
        }

    }

    public static void listReports(ArrayList<Transaction>transactions) {
        while(true) {
            System.out.println("List of the Reports");

            System.out.println("""
                    R) Reports
                    ▪ 1) Month To Date
                    ▪ 2) Previous Month
                    ▪ 3) Year To Date
                    ▪ 4) Previous Year
                    ▪ 5) Search by Vendor
                    ▪ 0) Back
                    """);

            String choice = input.nextLine().toUpperCase();

            switch (choice) {
                case "1":
                    monthToDate(transactions);
                    break;
                case "2":
                    previousMonth(transactions);
                    break;
                case "3":
                    yearToDate(transactions);
                    break;
                case "4":
                    previousYear(transactions);
                    break;
                case "5":
                    searchByVendor(transactions);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");


            }


        }
    }

    public static void monthToDate(ArrayList<Transaction>transactions) {

        System.out.println("\n--- MONTH TO DATE ---");

        LocalDate today = LocalDate.now();


        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);

            LocalDate date = LocalDate.parse(transaction.getDate(), formatter);

            if (date.getMonth() == today.getMonth() &&
                date.getYear() == today.getYear()) { // example: April
                System.out.println(
                            transaction.getDate() + " | " +
                            transaction.getTime() + " | " +
                            transaction.getDescription() + " | " +
                            transaction.getVendor() + " | " +
                            transaction.getAmount());

            }
        }
    }

    public static void previousMonth(ArrayList<Transaction>transactions) {
        System.out.println("\n--- PREVIOUS MONTH ---");

        LocalDate lastMonth = LocalDate.now().minusMonths(1);

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);

            LocalDate date = LocalDate.parse(t.getDate(), formatter);

            if (date.getMonth() == lastMonth.getMonth() &&
                    date.getYear() == lastMonth.getYear()) {

                System.out.println(
                        t.getDate() + " | " +
                                t.getTime() + " | " +
                                t.getDescription() + " | " +
                                t.getVendor() + " | " +
                                t.getAmount()
                );
            }
        }
    }

    public static void yearToDate(ArrayList<Transaction>transactions) {
        System.out.println("\n--- YEAR TO DATE ---");

        int year = LocalDate.now().getYear();


        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);

            LocalDate date = LocalDate.parse(transaction.getDate(), formatter);



            if (date.getYear() == year) {
                System.out.println
                        (transaction.getDate() + " | " +
                         transaction.getTime() + " | " +
                         transaction.getDescription() + " | " +
                         transaction.getVendor() + " | " +
                                transaction.getAmount());

            }
        }
        }

    public static void previousYear(ArrayList<Transaction>transactions){

        System.out.println("\n--- PREVIOUS YEAR ---");


        int lastYear = LocalDate.now().getYear() - 1;

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);

            LocalDate date = LocalDate.parse(transaction.getDate(), formatter);


            if (date.getYear() == lastYear)

             {
                System.out.println
                        (transaction.getDate() + " | " +
                         transaction.getTime() + " | " +
                         transaction.getDescription() + " | " +
                         transaction.getVendor() + " | " +
                         transaction.getAmount());
            }
        }
    }

    public static void searchByVendor(ArrayList<Transaction>transactions) {
        System.out.print("Enter the Vendor Name: ");
        String vendorName = input.nextLine().toLowerCase();
        System.out.println("\n--- RESULTS ---");

        boolean found = false;


        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);

            if (transaction.getVendor().toLowerCase().contains(vendorName)) {
                System.out.println
                        (transaction.getDate() + " | " +
                                transaction.getTime() + " | " +
                                transaction.getDescription() + " | " +
                                transaction.getVendor() + " | " +
                                transaction.getAmount());

                found = true;
            }
            }
            if (!found) {
                System.out.println("No transactions found for vendor: " + vendorName);
        }
    }
    public static void readTransaction(ArrayList<Transaction> transactions) {
        try (Scanner fileScanner = new Scanner(
                new java.io.File("src/main/resources/transactions.csv"))) {

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\|");

                if (parts.length == 5) {
                    String date = parts[0];
                    String time = parts[1];
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);

                    Transaction transaction = new Transaction(date, time, description, vendor, amount);
                    transactions.add(transaction);
                }
            }

        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void writeTransaction(Transaction transaction)
            throws IOException {
        try (BufferedWriter writeBuffer = new BufferedWriter(new FileWriter
                ("src/main/resources/transactions.csv", true))) {

            String date = LocalDate.now().toString();
            String time = LocalTime.now().toString().substring(0, 8);


            writeBuffer.write(
                    transaction.getDate() + "|" +
                        transaction.getTime() + "|" +
                        transaction.getDescription() + "|" +
                        transaction.getVendor() + "|" +
                        transaction.getAmount());


            writeBuffer.newLine();


        } catch (IOException e) {
            System.out.println("pls try again" + e.getMessage());



        }

    }
}
