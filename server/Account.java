import java.util.*;
import java.io.Serializable;

public class Account implements Serializable {
    int identification;
    int balance;
    public Account(int identification, int balance) {
        this.identification = identification;
        this.balance = balance;
    }
    public int balance() {
        return this.balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
}