package pl.sdacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.bank.BankAcount;
import pl.sdacademy.bank.BankAcountRepository;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/bank-account")
public class Controller {
    private BankAcountRepository bankAcountRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    public Controller(BankAcountRepository bankAcountRepository) {
        this.bankAcountRepository = bankAcountRepository;
    }

    @PostMapping("/post")
    public void add(@RequestBody BankAcount bankAcount) {
        bankAcountRepository.save(bankAcount);
    }


    @GetMapping
    public List<BankAcount> get() {
        return bankAcountRepository.findAll();
    }

    @GetMapping("/{id}")
    public BankAcount get(@PathVariable int id) {
        try {
            return bankAcountRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            LOGGER.info("Couldn't find this account");
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/{id}")
    public void update(@RequestBody BankAcount bankAcount, @PathVariable int id) {
        try {
            BankAcount acount = bankAcountRepository.findById(id).get();
            acount.setOwner(bankAcount.getOwner());
            acount.setAcountNumber(bankAcount.getAcountNumber());
            acount.setAmount(bankAcount.getAmount());
            acount.setAccountType(bankAcount.getAccountType());
            bankAcountRepository.save(acount);
        } catch (NoSuchElementException e) {
            LOGGER.info("Couldn't find this account");
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable int id) {
        try {
            bankAcountRepository.delete(bankAcountRepository.findById(id).get());
            LOGGER.info("account deleted successful");
        } catch (Exception e) {
            LOGGER.info("Couldn't delete this account");
            e.printStackTrace();
        }
    }

}
