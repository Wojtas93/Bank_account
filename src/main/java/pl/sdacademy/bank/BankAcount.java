package pl.sdacademy.bank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Currency;

@Entity
public class BankAcount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String owner;
    private Currency amount;
    private String acountNumber;
    private AccountType accountType;

    public BankAcount() {
    }

    public BankAcount(String owner, Currency amount, String acountNumber, AccountType accountType) {
        this.owner = owner;
        this.amount = amount;
        this.acountNumber = acountNumber;
        this.accountType = accountType;
    }

    public Integer getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public Currency getAmount() {
        return amount;
    }

    public String getAcountNumber() {
        return acountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setAmount(Currency amount) {
        this.amount = amount;
    }

    public void setAcountNumber(String acountNumber) {
        this.acountNumber = acountNumber;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "BankAcount{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", amount=" + amount +
                ", acountNumber='" + acountNumber + '\'' +
                ", accountType=" + accountType +
                '}';
    }

    public enum AccountType {
        CORPORATE,
        PERSONAL
    }
}

