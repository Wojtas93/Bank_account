package pl.sdacademy.bank;

import pl.sdacademy.person.Person;

import javax.persistence.*;
import java.util.Currency;
import java.util.List;

@Entity
public class BankAcount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Person owner;
    private Currency amount;
    private String acountNumber;
    private AccountType accountType;
    @OneToMany
    private List<Person> permision;

    public BankAcount() {
    }

    public BankAcount(Person owner, Currency amount, String acountNumber, AccountType accountType) {
        this.owner = owner;
        this.amount = amount;
        this.acountNumber = acountNumber;
        this.accountType = accountType;
    }

    public Integer getId() {
        return id;
    }

    public Person getOwner() {
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

    public void setOwner(Person owner) {
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

    public void setPermision(List<Person> permision) {
        this.permision = permision;
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

