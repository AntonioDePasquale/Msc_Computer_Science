import java.util.HashSet;
import java.util.Set;

public final class Name {

    private final Set<String> studentNames = new HashSet<>();
    private final Set<String> staffNames = new HashSet<>();

    private final String firstName;
    private final String lastName;
    private final String fullName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
    }

    public Set<String> getStudentNames() {
        return studentNames;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return fullName;
    }
}
