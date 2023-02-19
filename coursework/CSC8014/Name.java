import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public final class Name {

    private final Set<String> studentNames = new HashSet<>();
    private final Set<String> staffNames = new HashSet<>();


    public Set<String> getStudentNames() {
        return studentNames;
    }
}
