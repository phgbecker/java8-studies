package util;

import entity.Person;

import java.util.function.Consumer;

public class DisplayPersonName implements Consumer<Person> {

    @Override
    public void accept(Person person) {
        System.out.format("Name: %s\n", person.getName());
    }
}
