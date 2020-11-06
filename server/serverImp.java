import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.*;
import java.lang.String;
import java.net.*;
import java.io.*;


public class serverImp extends UnicastRemoteObject implements BankInterface {
    ArrayList<String> OperationsList;
    Hashtable<Integer, Account> AccountsList;
    public static Registry registry;

    public serverImp() throws RemoteException {
        super();
        this.OperationsList = new ArrayList<String>();
        this.AccountsList = new Hashtable<Integer, Account>();
    }

    public boolean AccountExistence(int identification) {
        return this.AccountsList.containsKey(identification);
    }

    public boolean CreateAccount(int identification, int balance) {
        if (!AccountExistence(identification)) {
            Account acc = new Account(identification, balance);
            this.AccountsList.put(identification, acc);
            Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
            OperationsList.add(hour+":"+minute+":"+second+"-Le client "+identification+"a créé avec un solde de "+balance);
            return true;
        }
        else {
            return false;
        }
    }

    public int balance(int identification) throws RemoteException {
        Account acc = AccountsList.get(identification);
        Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);
        OperationsList.add(hour+":"+minute+":"+second+"-Le client "+identification+"a créé avec un solde de "+acc.balance());
        return acc.balance();
    }

    public void Deposite(int identification, int balance) throws RemoteException {
        Account acc = AccountsList.get(identification);
        Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);
        OperationsList.add(hour+":"+minute+":"+second+"-Le client "+identification+"a déposite un montant "+balance);
        acc.setBalance(acc.balance() + balance);
    }

    public void Withdrawal(int identification, int balance) throws RemoteException {
        Account acc = AccountsList.get(identification);
        Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);
        OperationsList.add(hour+":"+minute+":"+second+"-Le client "+identification+"a retrait un montant "+balance);
        acc.setBalance(acc.balance() - balance);
    }

    public String ShowOperations() {
        String oper = "";
        for (String str : OperationsList) {
            oper += str + "\n";
        }
        return oper;
    }

    public static void main(String[] args) {
        try {
            int port = 1101;
            registry = LocateRegistry.createRegistry(port);
            System.setProperty("java.rmi.server.hostname", "localhost");
            Registry registry = LocateRegistry.getRegistry(port);

            serverImp bank = new serverImp();
            String name = "bank";
            registry.rebind(name, bank);
            System.out.println("Server registered!");

            Scanner scanner = new Scanner(System.in);
            int identification = Integer.parseInt(scanner.nextLine());

            registry.unbind(name);
            System.out.println("Server disconnected!");
        }
        catch (Exception e) {
            System.err.println("Error: " +e);
        }

    }

}