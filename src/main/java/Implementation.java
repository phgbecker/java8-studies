import entity.Gender;
import entity.Person;
import util.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Implementation {

    public static void main(String[] args) {

        List<Person> person = Arrays.asList(
                new Person("Peter", "peter@domain.com", Gender.MALE),
                new Person("Ana", "ana@domain.com", Gender.FEMALE),
                new Person("Amanda", "amanda@foo.com", Gender.FEMALE),
                new Person("Sunshine", "sunshine@domain.com", Gender.QUEER)
        );


        // Main stream supplier
        Supplier<Stream<Person>> personSupplier = () -> person.stream();


        // Sort by name, then prints
        personSupplier.get()
                .sorted(Comparator.comparing(Person::getName))
                .forEach(System.out::println);


        // Sort by name, maps the name attribute, uppercase it, then prints
        personSupplier.get()
                .sorted(Comparator.comparing(Person::getName))
                .map(p -> p.getName().toUpperCase())
                .forEach(System.out::println);


        // Find first person, then prints if present
        personSupplier.get()
                .findFirst()
                .ifPresent(System.out::println);


        // Usage of a concrete class that implements the Consumer<T> interface
        personSupplier.get()
                .forEach(new DisplayPersonName());


        // Anonymous implementation of the Consumer<T> interface
        Consumer<Person> displayPersonEmail = (p) -> System.out.format("Email: %s\n", p.getEmail());
        personSupplier.get()
                .forEach(displayPersonEmail);


        // Count the number of male
        System.out.format("Total of males: %d\n",
                personSupplier.get()
                        .filter(p -> Gender.MALE.equals(p.getGender()))
                        .count()
        );


        // Count the number of female
        System.out.format("Total of females: %d\n",
                personSupplier.get()
                        .filter(p -> Gender.FEMALE.equals(p.getGender()))
                        .count()
        );


        // Group persons by gender, counts them, then prints each group
        personSupplier.get()
                .collect(
                        Collectors.groupingBy(
                                Person::getGender,
                                Collectors.counting()
                        )
                )
                .forEach((gender, total) -> System.out.format("Gender: %s (%d)\n", gender, total));


        // Group persons by gender, counts them, then return a new Map Object
        Map<Gender, Long> genders = personSupplier.get()
                .collect(
                        Collectors.groupingBy(
                                Person::getGender,
                                Collectors.counting()
                        )
                );


        // Usage a concrete class that implements the BiConsumer<K, V> interface
        genders.forEach(new DisplayGender());


        // Anonymous implementation of the BiConsumer<K, V> interface
        BiConsumer<Gender, Long> displayGender = (gender, total) -> System.out.format("Gender: %s (%d)\n", gender, total);
        genders.forEach(displayGender);


        // Usage of a concrete class that implements the Validator<T> and Consumer<T> interface
        personSupplier.get()
                .forEach(new EmailValidator());


        // Anonymous implementation of the Validator<T> interface
        Validator<String> emailValidator = (e) -> e.matches("[a-z]+@foo.com");
        personSupplier.get()
                .forEach(p -> System.out.println(emailValidator.validate(p.getEmail())));


        // Builds a person object with the Function<T, R> interface
        Function<String, Person> personNameConstructor = Person::new;
        Person p1 = personNameConstructor.apply("Francis");
        System.out.println(p1);


        // Builds a person object with the BiFunction<T, U, R> interface
        BiFunction<String, String, Person> personNameEmailConstructor = Person::new;
        Person p2 = personNameEmailConstructor.apply("Davis", "davis@domain.com");
        System.out.println(p2);


        // Builds a person object with the TriFunction<T, U, V, R> interface
        TriFunction<String, String, Gender, Person> personNameEmailGenderConstructor = Person::new;
        Person p3 = personNameEmailGenderConstructor.apply("Samantha", "samantha@domain.com", Gender.FEMALE);
        System.out.println(p3);


        // Returns a list of the first 10 emails from the person list
        List<String> personEmails = personSupplier.get()
                .map(Person::getEmail)
                .limit(5)
                .collect(Collectors.toList());


    }

}
