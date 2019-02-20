package entity;

public enum Gender {
    FEMALE("Female"),
    MALE("Male"),
    QUEER("Queer");

    private String description;

    Gender(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

}
