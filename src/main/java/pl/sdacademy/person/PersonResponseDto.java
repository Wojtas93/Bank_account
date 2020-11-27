package pl.sdacademy.person;

import java.util.ArrayList;
import java.util.List;


public class PersonResponseDto {
    private List<Person> people = new ArrayList<>();

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }
}