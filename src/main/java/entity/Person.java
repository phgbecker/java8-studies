package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
    private String name;
    private String email;
    private Gender gender;

    public Person(String name, String email, Gender gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return
                new StringBuilder()
                        .append(name)
                        .append(" - ")
                        .append(email)
                        .append(" - ")
                        .append(gender)
                        .toString();
    }
}
