package ui;

public enum Gender {
    Male("Male"),
    Female("Female"),
    Other("Other");

    private final String displayNameGender;

    Gender(String displayName) {
        this.displayNameGender = displayName;
    }

    public String getDisplayName() {
        return displayNameGender;
    }
}
