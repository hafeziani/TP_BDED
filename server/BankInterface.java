import java.rmi.Remote;

public interface BankInterface extends Remote {
    public boolean CreateAccount(int identification,int balance) throws java.rmi.RemoteException;
    public void Deposite(int identification, int balance) throws java.rmi.RemoteException;
    public void Withdrawal(int identification, int balance) throws java.rmi.RemoteException;
    public int balance(int identification) throws java.rmi.RemoteException;
    public boolean AccountExistence(int identification) throws java.rmi.RemoteException;
    public String ShowOperations() throws java.rmi.RemoteException;
}
