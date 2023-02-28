import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class Researcher extends AbstractStaff {

    private static final Set<Name> studentNameSet = new HashSet<>();

    public Researcher(SmartCard card, String employmentStatus, String staffType, StaffID id) {
        super(card, employmentStatus, staffType, id);
    }

    /**
     * Returns the set of student names supervised by the researcher.
     * @return Set of student name objects.
     */
    Set<Name> getStudentNameSet() {
        return studentNameSet;
    }

    public Set<Name> addNameList(Set<Name> names) {
        studentNameSet.addAll(names);
        return studentNameSet;
    }

    /**
     * Returns true if supervisor is supervising enough students, false otherwise.
     * 10 students are required in total.
     *
     * @return true or false.
     */
    static Boolean supervisingTenStudentsCheck() {
        if (studentNameSet.size() >= 10) {
            return true;
        } else {
            return false;
        }
    }

    public static void addStudentNames(Set<Name> studentNames) {
        Iterator<Name> studentNamesIterator = studentNames.iterator();

        while (studentNamesIterator.hasNext()) {
            Name currentName = studentNamesIterator.next();
            if (!supervisingTenStudentsCheck()) {
                studentNameSet.add(currentName);
            }
        }
    }
}
