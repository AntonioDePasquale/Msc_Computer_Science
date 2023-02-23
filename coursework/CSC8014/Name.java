import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public final class Name {

    private final Set<String> studentNames = new HashSet<>();
    private final Set<String> staffNames = new HashSet<>();

    private final String firstName;
    private final String lastName;
    private final String fullName;

    private Name(String firstName, String lastName, String fullName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
    }

    public static Name getInstance(String first, String last) {
        String fullName = first + " " + last;

        return new Name(first, last, fullName);
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
