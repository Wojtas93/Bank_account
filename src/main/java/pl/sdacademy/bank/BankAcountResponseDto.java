package pl.sdacademy.bank;

import java.util.ArrayList;
import java.util.List;


public class BankAcountResponseDto {
    private List<BankAcount> bakaccounts = new ArrayList<>();

    public List<BankAcount> getBakaccounts() {
        return bakaccounts;
    }

    public void setBakaccounts(List<BankAcount> bakaccounts) {
        this.bakaccounts = bakaccounts;
    }
}