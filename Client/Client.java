import java.rmi.*;
import java.lang.*;
import java.util.*;
import java.rmi.registry.*;


public class Client {
    public static Scanner scanner;
    public static BankInterface bank;

    public Client() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1101);
            bank = (BankInterface) registry.lookup("bank");
        }
        catch (Exception e) {
            System.out.println("Client exception: " +e);
        }
    }
// BANK MENU AND ITS OPERATIONS
    public static String BankMenu() {
        String menu = "1. Create Account \n";
        menu += "2. Deposite \n";
        menu += "3. Withdrawal \n";
        menu += "4. Report \n";
        return menu;
    }
    public static void Home() {
        System.out.println(BankMenu());
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().toString();
        switch(choice) {
            case "1" : CreateAccount();
            case "2" : Deposite();
            case "3" : Withdrawal();
            case "4" : Report();
            case "5" : return;
        }
    }
// ACCOUNT CREATION FUNCTION
    public static void CreateAccount() {
        System.out.println("Enter your identification: ");
        Scanner scanner = new Scanner(System.in);
        int identification = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter your balance: ");
        int balance = Integer.parseInt(scanner.nextLine());

        try {
            bank.CreateAccount(identification, balance);
            System.out.println("Account created!");
        }
        catch (Exception e) {
            e.toString();
        }
        Home();
    }

// DEPOSITE FUNCTION
    public static void Deposite() {
        System.out.println("Enter your identification: ");
        Scanner scanner = new Scanner(System.in);
        int identification = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter your deposite amount: ");
        int balance = Integer.parseInt(scanner.nextLine());

        try {
            bank.Deposite(identification, balance);
            System.out.println("Money deposite!");
        }
        catch (RemoteException e) {
            e.toString();
        }
        Home();
    }

    public static void Deposite(int identification, int balance) {
        try {
            bank.Deposite(identification, balance);
        }
        catch (RemoteException e) {
            e.toString();
        }
    }

// WITHDRAWAL FUNCTION
public static void Withdrawal() {
    System.out.println("Enter your identification: ");
    Scanner scanner = new Scanner(System.in);
    int identification = Integer.parseInt(scanner.nextLine());

    System.out.println("Enter your Withdrawal amount: ");
    int balance = Integer.parseInt(scanner.nextLine());

    try {
        bank.Withdrawal(identification, balance);
        System.out.println("Money withdrawaled!");
    }
    catch (RemoteException e) {
        e.toString();
    }
    Home();
}

public static void Withdrawal(int identification, int balance) {
    try {
        bank.Withdrawal(identification, balance);
    }
    catch (RemoteException e) {
        e.toString();
    }
}

// REPORT
public static void Report() {
    System.out.println("Enter your identification: ");
    Scanner scanner = new Scanner(System.in);
    int identification = Integer.parseInt(scanner.nextLine());

    try {
        if (bank.AccountExistence(identification)) {
            System.out.println("Your balance is: " +bank.balance(identification));
        }
    }
    catch (RemoteException e) {
        e.toString();
        connection();
    }
    Home();
}

public static void Report(int identification) {
    try {
        if (bank.AccountExistence(identification)) {
            bank.balance(identification);
        }
    }
    catch (RemoteException e) {
        e.toString();
    }

}

// CONNECTION
public static void connection() {
    try {
        Registry registry = LocateRegistry.getRegistry("localhost", 1101);
        bank = (BankInterface) registry.lookup("bank");
        System.out.println("Reconnected!");
    }
    catch (Exception e) {
        e.toString();
    }
}

// MAIN
public static void main(String args[]) {
    try {
        Registry registry = LocateRegistry.getRegistry("localhost", 1101);
        bank = (BankInterface) registry.lookup("bank");
        System.out.println("Connected!");
        Home();
    }
    catch (Exception e) {
        System.out.println("Account exception: " +e);
    }
}

}