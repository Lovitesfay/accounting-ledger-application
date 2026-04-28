import java.util.Scanner;

public class AccountingLedgerApp {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("""
                
                What do you want to do?
                     1.Home
                     2 Ledger
                 Make you selection:
                """);

        int mainScreen = input.nextInt();

        switch (mainScreen) {
            case 1:
                HomePage();
                break;

            case 2:
                Ledger();
                break;

        }

    }

    public static void HomePage() {
        System.out.println("""
                ______Home Screen_______
                
                Choose from the list:
                
                ▪ Add Deposit - (D)
                ▪ P) Make Payment
                ▪ L) Ledger
                ▪ X) Exit
                """);


        int homepage = input.nextInt();


    }

    public static void Ledger() {

        int ledger = input.nextInt();

    }
}