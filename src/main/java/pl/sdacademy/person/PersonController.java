package pl.sdacademy.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@RestController
@RequestMapping("/person")
public class PersonController {
    private PersonRepository personRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping("/post")
    public ResponseEntity<String> add(@RequestBody Person person) {
        try {
            personRepository.save(person);
            return ResponseEntity.ok("Person saved");
        } catch (Exception e) {
            LOGGER.info("Couldn't save this person");
            e.printStackTrace();
            return new ResponseEntity(
                    "Couldn't save this person",
                    HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping
    public ResponseEntity<PersonResponseDto> get() {
        try {
            PersonResponseDto personResponseDto = new PersonResponseDto();
            personResponseDto.setPeople(personRepository.findAll());
            return ResponseEntity.ok(personResponseDto);
        } catch (NoSuchElementException e) {
            LOGGER.info("Couldn't find this person");
            e.printStackTrace();
            return new ResponseEntity(
                    "Couldn't find this person",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> get(@PathVariable int id) {
        try {
            return ResponseEntity.ok(personRepository.findById(id).get());
        } catch (NoSuchElementException e) {
            LOGGER.info("Couldn't find this person");
            e.printStackTrace();
            return new ResponseEntity(
                    "Couldn't find this person",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> update(@RequestBody Person nyPerson, @PathVariable int id) {
        try {
            Person oldPerson = personRepository.findById(id).get();
            oldPerson.setName(nyPerson.getName());
            oldPerson.setLastname(nyPerson.getLastname());
            oldPerson.setBirthdate(nyPerson.getBirthdate());
            oldPerson.setPersonalNumber(nyPerson.getPersonalNumber());
            personRepository.save(oldPerson);
            return ResponseEntity.ok("Person updated");
        } catch (NoSuchElementException e) {
            LOGGER.info("Couldn't person this account");
            e.printStackTrace();
            return new ResponseEntity(
                    "Couldn't person this account",
                    HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            personRepository.delete(personRepository.findById(id).get());
            LOGGER.info("person deleted successful");
            return ResponseEntity.ok("person deleted");
        } catch (Exception e) {
            LOGGER.info("Couldn't delete this person");
            e.printStackTrace();
            return new ResponseEntity(
                    "Couldn't deleted this person",
                    HttpStatus.BAD_REQUEST);
        }
    }


}
