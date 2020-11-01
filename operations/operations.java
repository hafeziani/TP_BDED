import java.rmi.*;
import java.lang.*;
import java.util.*;
import java.rmi.registry.*;

public class operations {
    public static void main (String args[]) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1101);
            BankInterface bank = (BankInterface) registry.lookup("bank");
            System.out.println(bank.ShowOperations());
        }
        catch (Exception e) {
            System.out.println("Client exception: " +e);
        }
    }
}