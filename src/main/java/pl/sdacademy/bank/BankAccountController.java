package pl.sdacademy.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.person.Person;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/bank-account")
public class BankAccountController {
    private BankAcountRepository bankAcountRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountController.class);

    public BankAccountController(BankAcountRepository bankAcountRepository) {
        this.bankAcountRepository = bankAcountRepository;
    }

    @PostMapping("/post")
    public ResponseEntity<String> add(@RequestBody BankAcount bankAcount) {
        try {
            bankAcountRepository.save(bankAcount);
            return ResponseEntity.ok("Account saved");
        }catch (Exception e){
            LOGGER.info("Couldn't save this account");
            e.printStackTrace();
            return new ResponseEntity(
                    "Couldn't save this account",
                    HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping
    public ResponseEntity<BankAcountResponseDto> get() {
        try {
            BankAcountResponseDto bankAcountResponseDto = new BankAcountResponseDto();
            bankAcountResponseDto.setBakaccounts(bankAcountRepository.findAll());
            return ResponseEntity.ok(bankAcountResponseDto);
        } catch (NoSuchElementException e) {
            LOGGER.info("Couldn't find this account");
            e.printStackTrace();
            return new ResponseEntity(
                    "Couldn't find this account",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAcount> get(@PathVariable int id) {
        try {
            return ResponseEntity.ok(bankAcountRepository.findById(id).get());
        } catch (NoSuchElementException e) {
            LOGGER.info("Couldn't find this account");
            e.printStackTrace();
            return new ResponseEntity(
                    "Couldn't find this account",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> update(@RequestBody BankAcount bankAcount, @PathVariable int id) {
        try {
            BankAcount account = bankAcountRepository.findById(id).get();
            account.setOwner(bankAcount.getOwner());
            account.setAcountNumber(bankAcount.getAcountNumber());
            account.setAmount(bankAcount.getAmount());
            account.setAccountType(bankAcount.getAccountType());
            bankAcountRepository.save(account);
            return ResponseEntity.ok("Account updated");
        } catch (NoSuchElementException e) {
            LOGGER.info("Couldn't update this account");
            e.printStackTrace();
            return new ResponseEntity(
                    "Couldn't update this account",
                    HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            bankAcountRepository.delete(bankAcountRepository.findById(id).get());
            LOGGER.info("account deleted successful");
            return ResponseEntity.ok("Account deleted");
        } catch (Exception e) {
            LOGGER.info("Couldn't delete this account");
            e.printStackTrace();
            return new ResponseEntity(
                    "Couldn't deleted this account",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{accountId}/permission/{personId}")
    public ResponseEntity<String> permission(@RequestBody List<Person> persons, @PathVariable int accountId, @PathVariable int personId) {
        try {
            BankAcount bankAcount = bankAcountRepository.findById(accountId).get();
            bankAcount.setPermision(persons);
            return ResponseEntity.ok("permission added");
        } catch (NoSuchElementException e) {
            LOGGER.info("Couldn't find this account");
            e.printStackTrace();
            return new ResponseEntity(
                    "Couldn't find this account",
                    HttpStatus.BAD_REQUEST);
        }
    }

}
