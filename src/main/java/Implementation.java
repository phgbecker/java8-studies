import entity.Gender;
import entity.Person;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Implementation {

    public static void main(String[] args) {

        List<Person> person = Arrays.asList(
                new Person("Pedro Becker", "pedro@domain.com", Gender.MALE),
                new Person("Otavio Becker", "otavio@domain.com", Gender.MALE),
                new Person("Laudiceia Becker", "laudiceia@domain.com", Gender.FEMALE)
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

    }
}
