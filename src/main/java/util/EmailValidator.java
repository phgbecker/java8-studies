package util;

import entity.Person;

import java.util.function.Consumer;

public class EmailValidator implements Validator<Person>, Consumer<Person> {

    @Override
    public boolean validate(Person person) {
        return person.getEmail().matches("[a-z0-9]+@domain.com");
    }

    @Override
    public void accept(Person person) {
        System.out.println(validate(person));
    }
}
