package util;

import entity.Gender;

import java.util.function.BiConsumer;

public class DisplayGender implements BiConsumer<Gender, Long> {

    @Override
    public void accept(Gender gender, Long total) {
        System.out.format("Gender: %s (%d)\n", gender, total);
    }
}
