import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
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

        // it makes a list to hold transactions while the app runs
        // This is like temporary storage in memory
        ArrayList<Transaction> transactions = new ArrayList<>();


        // it starts the app by showing the home screen first
        // Everything the user does begins from here
        homeScreen(transactions);



            }


    public static void homeScreen(ArrayList<Transaction> transactions)
            throws IOException {


        // This loop keeps the home screen running forever until user exits
        // It keeps asking the user what they want to do
        while (true) {


            // This prints the main menu options to the user
            // It shows deposit, payment, ledger, or exit
            System.out.print("""
                    ========(Home)========
                    
                    ▪ D) Add Deposit 💸
                    ▪ P) Make Payment 💳
                    ▪ L) Ledger 💼
                    ▪ X) Exit 🏃
                    
                    What would you like to do today?
                    """);

            // This reads what the user types and makes it uppercase
            // So it doesn't matter if they type lowercase or uppercase
            String choice = input.nextLine().toUpperCase();


            // This decides what to do based on the user input
            // Each case runs a different method
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
                    System.exit(0);

                    // this shows if the user is not entering the right input
                    default:
                    System.out.println("Invalid option. Please try again.");
                    break;

            }

        }
    }

    public static void addDeposit(ArrayList<Transaction> transactions)
            throws IOException {

        // This tells the user to enter details for the deposit
        // It prepares to collect input from the user
        System.out.println("Please fill in the blank to Deposit");

        // this gives spaces between the lines
        System.out.println(" ");


        // This gets today's date and time automatically
        // So the user doesn't have to type it
        String date = LocalDate.now().toString();
        String time = LocalTime.now().toString().substring(0, 8);

        // These lines ask the user for deposit details
        // Description, vendor, and amount
        System.out.print("Enter Description: ");
        String describe = input.nextLine();
        System.out.println("Vendor Name: ");
        String vendor = input.nextLine();
        System.out.print("Enter amount: ");
        double amount = input.nextDouble();

        // this eats the line and clears buffer
        input.nextLine();

        // This makes sure the amount is always positive
        // Deposits should never be negative
        amount = Math.abs(amount);
        System.out.println(" ");

        // This creates a new transaction object with the data
        // Then adds it to the list
        Transaction transact = new Transaction(date, time, describe, vendor, amount);
        transactions.add(transact);

        // This writes the transaction into the file
        // So it is saved permanently
        writeTransaction(transact);


        // This confirms to the user that deposit was saved
        // Just a friendly message
        System.out.println("Thank you \uD83D\uDE0A, We've Received your deposit! ");
        System.out.println(" ");


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

        System.out.println("We've \uD83D\uDE0A, received your Payment! ");
        System.out.println(" ");
    }

    public static void ledgerScreen(ArrayList<Transaction> transactions) throws IOException {
        while (true) {
            System.out.println("""
                    ========(Ledger)========
                    
                    o A) All 🌍
                    o D) Deposits 💲
                    o P) Payments 💲
                    o R) Reports  📊
                    o H) Home 📍
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

        System.out.println("\n=============== ALL TRANSACTIONS ===============\n");

        // it reads the file and show everything inside it
        // No filtering, just print all lines
        String filePath = "src/main/resources/transactions.csv";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            // I created variable so that I can use it in lines 225 and 226
            String line;


            // it goes line by line until the file ends
            // Each line is one transaction
            while ((line = reader.readLine()) != null) {

                // this is the VIL (Very Important Line. without this line, my code will not show anything
                System.out.println(line);
                System.out.println(" ");
            }

            // This happens if the file can't be found
            // Usually wrong path or missing file
        } catch (FileNotFoundException e) {
            System.out.println("File not found");

            // This happens if something breaks while reading
            // Like file issues or read error
        } catch (IOException e) {
            System.out.println(" Error detected while reading file");
        }


    }

    public static void listDeposits(ArrayList<Transaction> transactions) {
        System.out.println("List of the Deposits made");
        System.out.println(" ");
        System.out.println("\n====== DEPOSITS ======\n");

        String filePath = "src/main/resources/transactions.csv";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;

            while ((line = reader.readLine()) != null) {

                // it splits the line using |
                // This gives me each column separately
                String[] parts = line.split("\\|");


                // If the line is broken or wrong, I skip it
                // this ! sign saved me
                // This prevents crashes
                if (parts.length != 5) {
                    continue;
                }

                // this skip the header row (the word "amount")
                // So it doesn't try to convert text to number
                if (parts[4].equalsIgnoreCase("amount")) {
                    continue;
                }

                // I convert the amount from text to number
                //this need the (amount) to function properly
                double amount = Double.parseDouble(parts[4]);

                // If it's positive, it's a deposit
                // Then it will print it
                if (amount > 0) {
                    System.out.println(line);
                    System.out.println(" ");
                }
            }


            reader.close();

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }


    }

    public static void listPayments(ArrayList<Transaction> transactions) {
        System.out.println("\n====== PAYMENTS ======\n");

        String filePath = "src/main/resources/transactions.csv";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\\|");

                if (parts.length != 5) {
                    continue;
                }

                if (parts[4].equalsIgnoreCase("amount")) {
                    continue;
                }
                double amount = Double.parseDouble(parts[4]);

                // If it's negative, it's a payment
                // Then it will print it
                if (amount < 0) {
                    System.out.println(line);
                    System.out.println(" ");
                }
            }


            reader.close();

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }


    }

    public static void listReports(ArrayList<Transaction> transactions) {
        while (true) {
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

    public static void monthToDate(ArrayList<Transaction> transactions) {

        System.out.println("\n--- MONTH TO DATE ---");


        // I want only transactions from this month
        // So I compare each date with today
        LocalDate today = LocalDate.now();

        String filePath = "src/main/resources/transactions.csv";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\\|");

                if (parts.length != 5) {
                    continue;
                }

                if (parts[4].equalsIgnoreCase("amount")) {
                    continue;
                }

                // I turn the text date into a real date
                // So I can compare it
                LocalDate date = LocalDate.parse(parts[0]);


                // If same month and same year, I print it
                // That means it's "month to date"
                if (date.getMonth() == today.getMonth() &&
                        date.getYear() == today.getYear()) {
                    System.out.println(line);
                    System.out.println(" ");
                }

            }


            reader.close();

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());

        }
    }

    public static void previousMonth(ArrayList<Transaction> transactions) {
        System.out.println("\n--- PREVIOUS MONTH ---");
        System.out.println(" ");

        String filePath = "src/main/resources/transactions.csv";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;

            // this tells it to subtract 1 from the current month
            LocalDate lastMonth = LocalDate.now().minusMonths(1);

            // Skip header so it doesn't break parsing
            reader.readLine();
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\\|");
                LocalDate date;

                try {
                    date = LocalDate.parse(parts[0]);
                } catch (Exception e) {
                    continue;
                }
                // if you find any transaction from last month for this year then print it
                if (date.getMonth() == lastMonth.getMonth() &&
                    date.getYear() == lastMonth.getYear())
                {
                    System.out.println(line);
                    System.out.println(" ");

                }


            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }


    public static void yearToDate(ArrayList<Transaction>transactions) {
        System.out.println("\n--- YEAR TO DATE ---");
        System.out.println(" ");

        String filePath = "src/main/resources/transactions.csv";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;

            // Get the current year so we only show this year's activity
            int currentYear = LocalDate.now().getYear();

            // skip header
            reader.readLine();

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\\|");

                LocalDate date;

                try {
                    date = LocalDate.parse(parts[0]);
                } catch (Exception e) {
                    continue;
                }
                    // print only transaction for the current year
                if (date.getYear() == currentYear) {
                    System.out.println(line);
                    System.out.println(" ");
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

    }
        public static void previousYear(ArrayList<Transaction>transactions){

        System.out.println("\n--- PREVIOUS YEAR ---");

            String filePath = "src/main/resources/transactions.csv";

            try {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));

                String line;

                // Determine last year so we can compare against it

                int lastYear = LocalDate.now().getYear()-1;

                // skip header
                reader.readLine();

                while ((line = reader.readLine()) != null) {

                    String[] parts = line.split("\\|");

                    LocalDate date;

                    try {
                        date = LocalDate.parse(parts[0]);
                    } catch (Exception e) {
                        continue;
                    }
                    // this tells it to only include transactions from last year
                    // Prevents it from mixing with current or older years
                    if (date.getYear() == lastYear) {
                        System.out.println(line);
                        System.out.println(" ");
                    }
                }

                reader.close();

            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }


    }

    public static void searchByVendor(ArrayList<Transaction>transactions) {

        // File path where all transactions are stored
        String filePath = "src/main/resources/transactions.csv";

        // Get the vendor name from the user for filtering results
        System.out.print("Enter vendor name: ");

        // whatever they type in change it to lowercase
        String vendorName = input.nextLine().toLowerCase();

        // This keeps track if we found at least one match
        boolean found = false;


        // Open the file so we can read it line by line
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            // Read each transaction line one at a time
            while ((line = reader.readLine()) != null) {


                // Split the line into parts using "|" separator
                String[] parts = line.split("\\|");
                if (parts.length < 5) continue;

                // Skip bad lines or header row so we don't crash
                if (parts[0].equalsIgnoreCase("date")) {
                    continue;
                }
                // finally it everything is good is show print it
                if (parts[3].toLowerCase().contains(vendorName)) {
                    System.out.println(line);
                    System.out.println(" ");
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No transactions found for vendor: " + vendorName);
            }

        } catch (IOException e) {
            System.out.println("File error");
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
