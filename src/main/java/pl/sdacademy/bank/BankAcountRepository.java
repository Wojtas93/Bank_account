package pl.sdacademy.bank;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BankAcountRepository extends JpaRepository<BankAcount, Integer> {

}
